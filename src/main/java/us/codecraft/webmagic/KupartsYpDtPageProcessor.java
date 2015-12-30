package us.codecraft.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

import org.springframework.web.util.HtmlUtils;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class KupartsYpDtPageProcessor implements PageProcessor {

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    private static final int voteNum = 1000;

    @Override
    public void process(Page page) {
    	String pageUrl = page.getRequest().getUrl();
    	if (page.getUrl().regex("ProCategory/Index/").match() || page.getUrl().regex("Products/list").match())
    	{
	        List<String> partsUrl = page.getHtml().xpath("//div[@class='md']//span/a/@href").all();
	        page.addTargetRequests(partsUrl);
	       // relativeUrl = page.getHtml().xpath("//div[@id='zh-question-related-questions']//a[@class='question_link']/@href").all();
	        //page.addTargetRequests(relativeUrl);
	        List<String> parts =  page.getHtml().xpath("//div[@class='mod_pro_pic clearfix']//li[@class='item']").all();
	        boolean exist = false;
	        for(String part:parts){
	            String detailUrl = new Html(part).xpath("//div[@class='hidtit t']//a/@href").toString();
	            page.addTargetRequest(detailUrl);
	        }
	        if (!exist)
	        {
	        	page.setSkip(true);
	        }
    	}else if (page.getUrl().regex("Products/Detail").match() ){
    		//获取店铺头信息
            page.putField("pageUrl", pageUrl);
    		
    		List<String> imgUrl = page.getHtml().xpath("//div[@class='ly_show clearfix']//*[@id='sml_pics_list']/li/img/@data-small").all();
            page.putField("imgUrl", imgUrl);
            
            String title = page.getHtml().xpath("//div[@class='ly_show clearfix']//div[@class='title fa_yh']//h1/text()").toString();
            page.putField("title", title);
            
            String price = page.getHtml().xpath("//div[@class='ly_show clearfix']//div[@class='title fa_yh']//*[@id='p_markprice']/text()").toString();
            page.putField("price", price);
            
            String oriPrice = page.getHtml().xpath("//div[@class='ly_show clearfix']//div[@class='title fa_yh']//del/text()").toString();
            page.putField("oriPrice", oriPrice);
            
            String oriAds = page.getHtml().xpath("//div[@class='ly_show clearfix']//dl[@class='item it_post_ads']//dd/text()").toString();
            page.putField("oriAds", oriAds);
            
            String stock = page.getHtml().xpath("//div[@class='ly_show clearfix']//*[@id='p_bas_stock']/text()").toString();
            page.putField("stock", stock);
            
            List<String> attrKey = page.getHtml().xpath("//div[@class='ly_main']//*[@id='mod-detail-attributes']//td[@class='de-feature']/text()").all();
            page.putField("attrKey", attrKey);
            
            List<String> attrValue = page.getHtml().xpath("//div[@class='ly_main']//*[@id='mod-detail-attributes']//td[@class='de-value']/text()").all();
            page.putField("attrValue", attrValue);
            
            
            String detailUrl = page.getHtml().xpath("//div[@class='ly_main']//*[@id='mod-detail-description']//div[@id='desc-lazyload-container']/@data-tfs-url").toString();
            page.putField("detailUrl", detailUrl);
            //page.addTargetRequest(detailUrl);
            
            
            String detailContent = page.getHtml().xpath("//div[@class='ly_main']//*[@id='mod-detail-description']//div[@id='desc-lazyload-container']/html()").toString();
            page.putField("detailContent", HtmlUtils.htmlEscape(detailContent));
        
    		
    		//获取详情
    		
    		
    		//获取产品参数
    	}
    	/*
    	else if (page.getUrl().regex("taobaocdn.com").match())
    	{
    		String detailCotent = page.getRawText().replace("var desc='", "").replace("';", "");
    		System.out.println(detailCotent);
    		page.putField("detailCotent", detailCotent);
    	}*/

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new KupartsYpDtPageProcessor()).
                //addUrl("http://www.kuparts.com/ProCategory/Index/").
                addUrl("http://item.kuparts.com/Products/Detail/79967514-65a7-4c58-b77b-691386310a96").
                addPipeline(new JsonFilePipeline("D:\\kujson2\\")).
                thread(5).
                run();
    }
}

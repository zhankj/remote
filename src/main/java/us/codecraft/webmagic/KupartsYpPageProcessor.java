package us.codecraft.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class KupartsYpPageProcessor implements PageProcessor {

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    private static final int voteNum = 1000;

    @Override
    public void process(Page page) {
        List<String> partsUrl = page.getHtml().xpath("//div[@class='md']//span/a/@href").all();
        page.addTargetRequests(partsUrl);
       // relativeUrl = page.getHtml().xpath("//div[@id='zh-question-related-questions']//a[@class='question_link']/@href").all();
        //page.addTargetRequests(relativeUrl);
        List<String> parts =  page.getHtml().xpath("//div[@class='mod_pro_pic clearfix']//li[@class='item']").all();
        boolean exist = false;
        for(String part:parts){
        	ResultItems resultItems = new ResultItems();
            String imgUrl = new Html(part).xpath("//a[@class='img']//img/@data-original").toString();
            page.putField("imgUrl", imgUrl);
            resultItems.put("imgUrl", imgUrl);
            String detailUrl = new Html(part).xpath("//div[@class='hidtit t']//a/@href").toString();
            page.putField("detailUrl", detailUrl);
            resultItems.put("detailUrl", detailUrl);
            String desc = new Html(part).xpath("//div[@class='hidtit t']//a/text()").toString();
            page.putField("desc", desc);
            resultItems.put("desc", desc);
            String entUrl = new Html(part).xpath("//div[@class='com hidtit_2']//a/@href").toString();
            page.putField("entUrl", entUrl);
            resultItems.put("entUrl", entUrl);
            String entName = new Html(part).xpath("//div[@class='com hidtit_2']//a/text()").toString();
            page.putField("entName", entName);
            resultItems.put("entName", entName);
            String price = new Html(part).xpath("//div[@class='price']/em/text()").toString();
            page.putField("price", price);
            resultItems.put("price", price);
            String oriPrice = new Html(part).xpath("//div[@class='price']/del/text()").toString();
            page.putField("oriPrice", oriPrice);
            resultItems.put("oriPrice", oriPrice);
            resultItems.setRequest(page.getRequest());
            page.putItems(resultItems);
            exist = true;
        }
        if (!exist)
        {
        	page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new KupartsYpPageProcessor()).
                addUrl("http://www.kuparts.com/ProCategory/Index/").
                addPipeline(new JsonFilePipeline("D:\\kujson2\\")).
                thread(5).
                run();
    }
}

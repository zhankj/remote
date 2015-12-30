package cn.cttic.craft;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class KupartsPageProcessor implements PageProcessor {

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
        List<String> parts =  page.getHtml().xpath("//div[@class='pro_pic_sml clearfix']//li[@class='item']").all();
        boolean exist = false;
        for(String part:parts){
            String imgUrl = new Html(part).xpath("//div[@class='img']//a/@href").toString();
            page.putField("img", imgUrl);
            String detailUrl = new Html(part).xpath("//div[@class='base']//div[@class='t']//a/@href").toString();
            page.putField("detailUrl", detailUrl);
            String desc = new Html(part).xpath("//div[@class='base']//div[@class='t']//a/text()").toString();
            page.putField("desc", desc);
            String entUrl = new Html(part).xpath("//div[@class='base']//div[@class='hidtit_2 com_name']//a/@href").toString();
            page.putField("entUrl", entUrl);
            String entName = new Html(part).xpath("//div[@class='base']//div[@class='hidtit_2 com_name']//a/text()").toString();
            page.putField("entName", entName);
            String price = new Html(part).xpath("//div[@class='price']/text()").toString();
            page.putField("price", price);
            String addr = new Html(part).xpath("//div[@class='ads']/text()").toString();
            page.putField("addr", addr);
            String sum = new Html(part).xpath("//div[@class='sum']//p/text()").toString();
            page.putField("sum", sum);
            String service = new Html(part).xpath("//div[@class='service']//div[@class='spt_pf']/text()").toString();
            page.putField("service", service);
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new KupartsPageProcessor()).
                addUrl("http://www.kuparts.com/ProCategory/PartsIndex").
                addPipeline(new FilePipeline("D:\\kuparts\\")).
                thread(5).
                run();
    }
}

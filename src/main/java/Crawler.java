import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    public static void main(String[] args) throws IOException {
        List<String> listOfUrls = getListOfUrls();
        for (String urlString : listOfUrls) {
            System.out.println(urlString);
        }
    }

    private static List<String> getListOfUrls() throws IOException {
        List<String> result = new ArrayList<>();
        String maineStateCityCatalogUrl = "https://www.memun.org/Training-Resources/Local-Government/Municipal-Websites";
        Document page = Jsoup.connect(maineStateCityCatalogUrl).get();

        Elements urlLists = page.getElementById("dnn_ctr3541_HtmlModule_lblContent").getElementsByTag("ul");
        for (Element urlList : urlLists) {
            Elements children = urlList.children();
            for (Element url : children) {
                if (!url.children().isEmpty()) {
                    result.add(url.child(0).childNode(0).toString());
                }
            }
        }
        return result;
    }
}

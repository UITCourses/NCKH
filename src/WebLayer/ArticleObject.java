package WebLayer;

import java.sql.Timestamp;
import java.util.List;

import DTO.ArticleDTO;
import DTO.FacebookDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import org.apache.commons.io.IOUtils;

public abstract class ArticleObject {

    protected String apiFBStart = "https://graph.facebook.com/fql?q=SELECT%20share_count,%20like_count,%20comment_count%20FROM%20link_stat%20where%20url=%27";
    protected String apiFBEnd = "%27";
    // 
    public abstract ArticleDTO getArticleInformation(String source_url);

    public abstract List<String> getMenuWeb(String source_url);
    // trong day se goi ham 
    public abstract List<ArticleDTO> getNewsOfEachMenuDependOnTime(String source_url, Timestamp newtime, Timestamp lasttime);
    
    // get article like
     public abstract int getArticleLike(int objectID);
     
    // facebook
    protected FacebookDTO getContentOfFacebook(String source_url) throws MalformedURLException, IOException {
        String  url = apiFBStart + source_url + apiFBEnd;

        FacebookDTO fb = new FacebookDTO();
        String json = IOUtils.toString(new URL(url).openStream(), "UTF-8");
        JsonParser parser = new JsonParser();

        JsonElement element = parser.parse(json);
        JsonObject root = element.getAsJsonObject();
        JsonArray datas = root.getAsJsonArray("data");
        JsonObject data = datas.get(0).getAsJsonObject();

        Gson gson = new Gson();
        fb = gson.fromJson(data, FacebookDTO.class);

        return fb;
        // khong quan trong
    }
    
    // Check if date of month valid
    protected boolean isTheDayOfMonthValid(ArticleDTO art, Timestamp lasttime) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(art.getArticleDate());

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(lasttime);
        if (cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH) >= 0) {
            return true;
        }
        return false;
    }
    
    

}

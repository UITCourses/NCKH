/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

/**
 *
 * @author Minh Nhat
 */
import DTO.*;
import DataAccessLayer.*;
import java.sql.SQLException;
import java.util.List;

public class ArticleBUS {

    // article -> parent comment -> sub comment
    private String username = null;
    private String password = null;
    private ArticleDAO artDAO = null;

    public ArticleBUS() {
    }

    // ok
    public ArticleBUS(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
        artDAO = new ArticleDAO(username, password);

    }

    // ok
    public boolean insertArticle(ArticleDTO art) {
        int maxIDTableArticle = getMaxIDTableArticle();
        art.setIDTableArticle(maxIDTableArticle + 1);
        art.setIDTableUpdateTime(1);
        art.setCountOfUpdate(0);
        return artDAO.insertArticle(username, password, art);
    }

    public boolean updateArticle(ArticleDTO art) {
        return artDAO.updateArticle(username, password, art);
    }

    
    public int getMaxIDTableArticle() {
        return artDAO.getMaxIDTableArticle(username, password);
    }
    
    public List<ArticleDTO> getArticleToUpdate(int IDTableUpdateTime , int IDTableMagazine){
        return artDAO.getArticleToUpdate(username, password, IDTableUpdateTime, IDTableMagazine);
    }
    
    // working with list
    public boolean insert(List<ArticleDTO> lart){
        for(ArticleDTO art : lart){
            if(insertArticle(art) == false)
                return false;
        }
        return true;
    }
    
    

}

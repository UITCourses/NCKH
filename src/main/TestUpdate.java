/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Minh Nhat
 */

import BusinessLayer.ArticleBUS;
import BusinessLayer.ParentCmtBUS;
import BusinessLayer.SubCmtBUS;
import DTO.ArticleDTO;
import DTO.ParentCmtDTO;
import DTO.SubCmtDTO;
import WebLayer.ArticleTuoiTre;
import WebLayer.ArticleObject;
import WebLayer.IParentComment;
import WebLayer.ISubComment;
import WebLayer.ParentCmtTuoiTre;
import WebLayer.SubCmtTuoiTre;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TestUpdate {
    public static void main(String[] args) throws SQLException
    {
         String username = "nhat";
        String password = "mysql!@3";
        
        List<ArticleDTO> lart = null;
        ArticleBUS artBUS = new ArticleBUS(username, password);
        
        lart= artBUS.getArticleToUpdate(1, 3);
        
         for (int i = 0; i < lart.size(); i++) {
            System.out.println(
                    "ObjectID : " + lart.get(i).getObjectID() +
                   "\nTitle : " + lart.get(i).getTitle() + "\nURL : " + lart.get(i).getUrl() + "\nCategory : "
                    + lart.get(i).getIDTableCategory() + "\nFb like : " + lart.get(i).facebook.getFBLike() + "\n");
            
        }
    }
}

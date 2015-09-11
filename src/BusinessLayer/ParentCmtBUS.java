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
import DataAccessLayer.*;
import DTO.*;
import java.sql.SQLException;
import java.util.List;
public class ParentCmtBUS {
    ParentCmtDAO pcmtDAO;
    private String username;
    private String password;
    
    public ParentCmtBUS(String username, String password) throws SQLException{
        this.username = username;
        this.password = password;
        pcmtDAO = new ParentCmtDAO(username, password);
    }
    
    public boolean insertParentComment(ParentCmtDTO par) {
        // generated idtable parent cmt
        int maxid = getMaxIDTableParentCmt();
        par.setIDTableParentCmt(maxid + 1);
        return pcmtDAO.insertParentCmt(username, password, par);
    }
    
    public boolean updateParentComment(ParentCmtDTO par){
        return pcmtDAO.updateParentCmt(username, password, par);
    }
    
    public int getMaxIDTableParentCmt (){
        return pcmtDAO.getMaxIDTableParentCmt(username, password);
    }
    
    public int isParentCmtExits (ParentCmtDTO par){
        return pcmtDAO.isParentCmtExits(username, password, par);
    }
    
    // working with list
    public boolean insert(List<ParentCmtDTO> lpar){
        for(ParentCmtDTO par : lpar){
            if(insertParentComment(par) == false)
                return false;
        }
        return true;
    }
    
}

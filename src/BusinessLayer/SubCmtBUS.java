package BusinessLayer;

import DTO.ParentCmtDTO;
import java.sql.SQLException;

import javax.naming.NamingException;

import DTO.SubCmtDTO;
import DataAccessLayer.SubCmtDAO;
import java.util.List;

public class SubCmtBUS {

    SubCmtDAO scmtDAO;

    private String username;
    private String password;

    public SubCmtBUS(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
        scmtDAO = new SubCmtDAO(username, password);
    }

    public boolean insertParentComment(SubCmtDTO child) {
        int maxIDTableSubCmt = getMaxIDTableSubCmt();
        child.setIDTableSubCmt(maxIDTableSubCmt + 1);
        
        int idTableParentCmt = getIDTableParentCmtWithArgument(child);
        child.setIDTableParentCmt(idTableParentCmt);
        return scmtDAO.insertSubCmt(username, password, child);
    }

    public boolean updateParentComment(SubCmtDTO child) {
        return scmtDAO.updateSubCmt(username, password, child);
    }

    public int getMaxIDTableSubCmt() {
        return scmtDAO.getMaxIDTableSubCmt(username, password);
    }

    public int isSubCmntExits(SubCmtDTO child) {
        return scmtDAO.isSubCmntExits(username, password, child);
    }

    public int getIDTableParentCmtWithArgument(SubCmtDTO sub) {
        return scmtDAO.getIDTableParentCmtWithArgument(username, password, sub);
    }
    
    // Working with list
    public boolean insert(List<SubCmtDTO> lchild){
        for(SubCmtDTO child : lchild){
            if(insertParentComment(child) == false)
                return false;
        }
        return true;
    }

}

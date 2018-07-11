import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeveloperBussiness {

    public List<DeveloperModel> getAllDevelopers(){
        List<DeveloperModel> developerModels = new ArrayList<>();
        String sql = "select * from developer";
        DBHelper dbHelper = new DBHelper(sql);
        ResultSet resultSet = null;
        try{
            resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModels.add(developerModel);
            }
            resultSet.close();
            dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return developerModels;
    }

    public DeveloperModel getDeveloper(String developerId){
        String sql = "select * from developer where id =" + developerId;
        DBHelper dbHelper = new DBHelper(sql);
        DeveloperModel developerModel = null;
        try{
            ResultSet resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
            }
            resultSet.close();
            dbHelper.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return developerModel;
    }

    public boolean addDeveloper(DeveloperModel developerModel){
        String sql = "insert into developer(name,site) values(" +
                "'"+developerModel.getName()+"',"
                +"'"+developerModel.getSite()+"'"+ ");";
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }

    private boolean execute(DBHelper dbHelper){
        try {
            dbHelper.preparedStatement.execute();
            dbHelper.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDeveloper(String id,String name){
        String sql = "update developer set name='" + name + "' where id=" + id;
        DBHelper dbHelper = new DBHelper(sql);
        try{
            dbHelper.preparedStatement.executeUpdate();
            dbHelper.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDeveloper(String id){
        String sql = "delete from developer where id = " + id;
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }

}

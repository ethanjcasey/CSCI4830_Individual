package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import javax.persistence.Transient;
/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "listuser")
public class ListUser {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "username")
   private String username;


   
   @Column(name = "todo")
   private String strList;
   
   @Transient
   private ArrayList<String> list;

   public ListUser() {
	   this.username = "";
	   this.strList = "";
   }

   public ListUser(Integer id, String name) {
      this.id = id;
      this.username = name;
      
      this.strList = "";
      this.list = new ArrayList<String>();
   }

   public ListUser(String name) {
      this.username = name;
      
      this.strList = "";
      this.list = new ArrayList<String>();
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return username;
   }

   public void setName(String name) {
      this.username = name;
   }
   
   public String getList() {
	   return strList;
   }
   
   public void setList(String s)
   {
	   this.strList = s;
   }
   
   public void addList(String s)
   {
	   if(strList.isEmpty()) 
		   strList += s;
	   else
		   strList += ", " + s;
   }
   
   @Override
   public String toString() {
      return "List for: " + this.username + "\n" + this.strList;
   }
   
   private String stringify()
   {
	   String s = "";
	   for(int i = 0; i < list.size(); i++) {
		   s += list.get(i) + "\n";
	   }
	   System.out.println(s);
	   return s;
   }
}
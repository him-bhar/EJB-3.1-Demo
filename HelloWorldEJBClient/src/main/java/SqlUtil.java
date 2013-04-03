import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


/**
 * Title: SQL utility functions
 * <p>
 *
 * Description: All methods in this class are static.
 * <p>
 *
 *
 *
 * @author David Ekholm, Mikael Staaldal
 * @version 1.0
 */
/**
 * @author Jose da Silva Avega AB
 *
 */
public class SqlUtil {

    private static final Logger LOG = Logger.getAnonymousLogger();

    public static DataSource getDataSource() throws NamingException {
    	DataSource source = getDataSource("unbranded");
    	//try {
    		System.out.println(source.getClass());
			/*System.out.println("Wrapper for NetEnt Data source: "+source.isWrapperFor(com.netent.jdbcdriver.datasource.DataSource.class));
			System.out.println("Wrapper for SQL Server source: "+source.isWrapperFor(SQLServerDataSource.class));*/
//		} catch (SQLException e) {
	//		// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
        return source;
    }


    public static DataSource getDataSource(String casinoId) throws NamingException {
        if (casinoId == null) {
            casinoId = "unbranded";
        }
        try {
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new NamingException("Context is null");
            }
            return (javax.sql.DataSource) ctx.lookup("java:jboss/datasources/ejb/" + casinoId);
        } catch (NamingException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}

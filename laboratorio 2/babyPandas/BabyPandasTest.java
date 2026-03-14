import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** BabyPandasTest.java
 *  Pruebas unitarias para BabyPandas – BDD (Ciclos 1, 2 y 3).
 *
 * @author ESCUELA 2026-01
 */
public class BabyPandasTest {

    private BabyPandas bp;

    // Datos compartidos de prueba
    private final String[][] TABLA = {
        {"Nombre",  "Edad", "Profesion"},     
        {"Lucía",   "28",   "Ingeniero"},        
        {"Carlos",  "35",   "Profesor"},         
        {"Ana",     "42",   "Doctor"},           
        {"Jorge",   "30",   "Arquitecto"},       
        {"Elena",   "25",   "Diseñador"},        
    };

    @Before
    public void setUp() {
        bp = new BabyPandas();
    }

    /** define() crea la variable correctamente → ok() == true */
    @Test
    public void shouldDefineVariable() {
        bp.define("df1");
        assertTrue(bp.ok());
    }

    /** define() con nombre vacío → ok() == false */
    @Test
    public void shouldNotDefineEmptyName() {
        bp.define("");
        assertFalse(bp.ok());
    }

    /** assign() guarda el DataFrame y shape retorna {5, 3} */
    @Test
    public void shouldAssignAndShape() {
        bp.define("df1");
        bp.assign("df1", TABLA);
        assertTrue(bp.ok());
        assertArrayEquals(new int[]{5, 3}, bp.shape("df1"));
    }

    /** assign() sin define previo → ok() == false */
    @Test
    public void shouldNotAssignWithoutDefine() {
        bp.assign("df1", TABLA);
        assertFalse(bp.ok());
    }
    
    /** shape() sobre variable definida y asignada → {5,3} y ok() == true */
    @Test
    public void shouldReturnCorrectShape() {
        bp.define("df1");
        bp.assign("df1", TABLA);  
        int[] result = bp.shape("df1");
        assertNotNull(result);
        assertArrayEquals(new int[]{5, 3}, result);
        assertTrue(bp.ok());
    }
    /** shape() sobre variable inexistente → null y ok() == false */
    @Test
    public void shouldNotShapeUndefined() {
        int[] result = bp.shape("noExiste");
        assertNull(result);
        assertFalse(bp.ok());
    }

    /** head() retorna las primeras 2 filas correctamente */
    @Test
    public void shouldHeadReturnRows() {
        bp.define("df1");
        bp.assign("df1", TABLA);
        String result = bp.head("df1", 2);
        assertNotNull(result);
        assertTrue(bp.ok());
        assertTrue(result.contains("Nombre"));
        assertTrue(result.contains("Lucía"));
        assertTrue(result.contains("Carlos"));
    }

    /** head() sobre variable sin asignar → null y ok() == false */
    @Test
    public void shouldNotHeadUndefined() {
        bp.define("df1");
        String result = bp.head("df1", 3);
        assertNull(result);
        assertFalse(bp.ok());
    }
    
    /** assignUnary('r') selecciona filas correctamente → ok() == true */
    @Test
    public void shouldSelectRows() {
        bp.define("df1");
        bp.assign("df1", TABLA);
        bp.define("df2");
        bp.assignUnary("df2", "df1", 'r', new String[]{"0", "2"});
        assertTrue(bp.ok());  
        int[] shape = bp.shape("df2");
        assertArrayEquals(new int[]{2, 3}, shape);
    }
    
    /** assignUnary('c') selecciona columnas correctamente → ok() == true */
    @Test
    public void shouldSelectColumns() {
        bp.define("df1");
        bp.assign("df1", TABLA);
        bp.define("df2");  
        bp.assignUnary("df2", "df1", 'c', new String[]{"Nombre", "Edad"});  
        assertTrue(bp.ok());   
        int[] shape = bp.shape("df2");
        assertArrayEquals(new int[]{5, 2}, shape);
    }
    
    /** assignUnary('?') filtra filas correctamente → ok() == true */
    @Test
    public void shouldFilterRowsByCondition() {
        bp.define("df1");
        bp.assign("df1", TABLA);
        bp.define("df2");
        bp.assignUnary("df2", "df1", '?', new String[]{"Nombre", "Ana"});   
        assertTrue(bp.ok());   
        int[] shape = bp.shape("df2");
        assertNotNull(shape);
    }
    
    /** assignUnary() sin variable destino definida → ok() == false */
    @Test
    public void shouldNotAssignUnaryIfDestinationUndefined() {
        bp.define("df1");
        bp.assign("df1", TABLA);
        bp.assignUnary("df2", "df1", 'r', new String[]{"0"});  
        assertFalse(bp.ok());
    }
    
    /** assignUnary() con variable fuente inexistente → ok() == false */
    @Test
    public void shouldNotAssignUnaryIfSourceUndefined() {
        bp.define("df2");
        bp.assignUnary("df2", "noExiste", 'r', new String[]{"0"});  
        assertFalse(bp.ok());
    }
    
    /** assignBinary 'r' concatena dos DF por filas → shape {10, 3} */
    @Test
    public void shouldConcatByRows() {
        bp.define("df1");  bp.assign("df1", TABLA);
        bp.define("df2");  bp.assign("df2", TABLA);
        bp.define("df3");
        bp.assignBinary("df3", "df1", 'r', "df2");
        assertTrue(bp.ok());
        assertArrayEquals(new int[]{10, 3}, bp.shape("df3"));
    }

    /** assignBinary 'c' concatena dos DF por columnas → shape {5, 6} */
    @Test
    public void shouldConcatByCols() {
        bp.define("df1");  bp.assign("df1", TABLA);
        bp.define("df2");  bp.assign("df2", TABLA);
        bp.define("df3");
        bp.assignBinary("df3", "df1", 'c', "df2");
        assertTrue(bp.ok());
        assertArrayEquals(new int[]{5, 6}, bp.shape("df3"));
    }

    /** assignBinary con variable destino sin define → ok() == false */
    @Test
    public void shouldNotConcatWithoutDefine() {
        bp.define("df1");  bp.assign("df1", TABLA);
        bp.define("df2");  bp.assign("df2", TABLA);
        bp.assignBinary("df3", "df1", 'r', "df2");
        assertFalse(bp.ok());
    }

    /** assignBinary con fuente inexistente → ok() == false */
    @Test
    public void shouldNotConcatUndefinedSource() {
        bp.define("df1");  bp.assign("df1", TABLA);
        bp.define("df3");
        bp.assignBinary("df3", "df1", 'r', "dfNoExiste");
        assertFalse(bp.ok());
    }

    @After
    public void tearDown() {
        bp = null;
    }
}
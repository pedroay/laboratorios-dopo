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
        {"Nombre",  "Edad", "Profesion"},       // fila 0 = columnas
        {"Lucía",   "28",   "Ingeniero"},        // fila 1
        {"Carlos",  "35",   "Profesor"},         // fila 2
        {"Ana",     "42",   "Doctor"},           // fila 3
        {"Jorge",   "30",   "Arquitecto"},       // fila 4
        {"Elena",   "25",   "Diseñador"},        // fila 5
    };

    @Before
    public void setUp() {
        bp = new BabyPandas();
    }

    // =======================================================================
    // CICLO 1 – Operaciones básicas: define, assign, shape, head
    // =======================================================================

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
        // Debe contener los nombres de las columnas y los primeros datos
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
}
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;





public class DataFrameTest{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }

    
     @Test
    public void shouldCreateSmallestDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data={};
        int [] shape={0,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());     
    }    
   
    @Test
    public void shouldCreateOtherDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {{"Carlos", "35", "Profesor"}, 
        {"Ana", "42", "Doctor"}, 
        {"Jorge", "30", "Arquitecto"},
        {"Elena", "25", "Diseñador"}};
        int [] shape={4,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape()); 
    }    
    
    @Test
    public void shouldNotCreateBadDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {{"Carlos", "35"}, 
        {"Ana", "42", "Doctor"}, 
        {"30", "Arquitecto"},
        {"Elena", "25", "Diseñador"}};
        int [] shape={2,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());
    }
    
    /**
     * La prueba pasa porque el DataFrame tiene exactamente 1 fila y el valor esperado coincide con el valor real. El assertEquals se cumple correctamente.
     */
    @Test
    public void shouldPass() {
        String [] columns = {"Nombre", "Edad"};
        String [][] data = {{"Carlos", "35"}};
        
        DataFrame df = new DataFrame(data, columns);
        
        assertEquals(1, df.shape()[0]);
    }
    
    /**
     * La prueba falla porque el DataFrame tiene 1 fila, pero el assertEquals espera 5. El programa se ejecuta normalmente, pero el resultado no coincide con lo esperado, por eso es un Failure.
     */
    @Test
    public void shouldFail() {
        String [] columns = {"Nombre", "Edad"};
        String [][] data = {{"Carlos", "35"}};
        
        DataFrame df = new DataFrame(data, columns);
        
        assertEquals(5, df.shape()[0]);  // Valor incorrecto
    }
    
    /**
     * La prueba genera un error porque se intenta ejecutar un método sobre un objeto null.
    Aquí no falla un assert, sino que ocurre una excepción (NullPointerException), por eso JUnit lo reporta como Error.
     */
    @Test
    public void shouldErr() {
        DataFrame df = null;
        
        df.shape();  // Genera NullPointerException
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}

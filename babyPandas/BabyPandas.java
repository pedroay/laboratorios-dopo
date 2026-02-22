import java.util.HashMap;



/** BabyPandas.java
 * 
 * @author ESCUELA 2026-01
 */
    
public class BabyPandas{
    
    private HashMap<String, DataFrame> variables;
    private boolean lastOperationOk;
    
    public BabyPandas() {
        variables        = new HashMap<>();
        lastOperationOk  = false;
    }


   /**
     * Define una nueva variable con valor null
     *
     * @param name Nombre de la variable a definir
     */
    public void define(String name) {
        boolean nameEmpty = name.isEmpty(); 
        if (name != null && !name.isEmpty()) {
            HashMap<String, DataFrame> variable = getVariables();
            variable.put(name, null);
            lastOperationOk = true;
        } else {
            lastOperationOk = false;
        }
    }
     
    /**
     * Asigna un DataFrame a una variable existente.
     * La primera fila de dataFrame se usa como columnas (header).
     * Las filas siguientes son los datos.
     *
     *
     * @param a         Nombre de la variable destino (debe estar definida)
     * @param dataFrame Matriz donde dataFrame[0] = columnas, dataFrame[1..] = datos
     */
    public void assign(String a, String[][] dataFrame) {
        HashMap<String, DataFrame> variable = getVariables();
        if (!variable.containsKey(a) || dataFrame == null || dataFrame.length < 1) {
            lastOperationOk = false;
            return;
        }
        
        // Primera fila = nombres de columnas
        String[]   columns = dataFrame[0];
        int lenDataFrame = dataFrame.length;
        String[][] data    = new String[dataFrame.length - 1][];
        for (int i = 1; i < lenDataFrame; i++) {
            data[i - 1] = dataFrame[i];
        }
        DataFrame newDataFrame = new DataFrame(data,columns);
        variable.put(a, newDataFrame);
        lastOperationOk = true;
    }  
        
    
    
    /**
     * Retorna las dimensiones {filas, columnas} del DataFrame almacenado en 'a'.
     * @param a Nombre de la variable
     * @return  int[]{filas, columnas} o null si la variable no existe / no tiene DataFrame
     */
    public int[] shape(String a) {
        DataFrame df = getDF(a);
        if (df == null) {
            lastOperationOk = false;
            return null;
        }
        lastOperationOk = true;
        return df.shape();
    }
    
      private DataFrame getDF(String name) {
        if (!variables.containsKey(name)) return null;
        return variables.get(name);
    }
    
    private HashMap<String, DataFrame> getVariables(){
        return variables;
    }
    
    
    //Assigns the value of a unary operation to a variable
    // a = b op parameters
    //The operator characters are: 'r' select rows, 'c' select columns, '?' select condition
    //The parameters for 'r' are [index1, index2, ...]
    //The parameters for 'c' are [column1, column2, ...]
    //The parameters for '?' are [valueColumn1, valueColumn2, ...]

        public void assignUnary(String a, String b, char op, String[] parameters) {
            if (!variables.containsKey(a)) {
                lastOperationOk = false;
                return;
            }
            DataFrame source = getDF(b);
            if (source == null) {
                lastOperationOk = false;
                return;
            }

            DataFrame result = null;

            switch (op) {

                case 'r': // Seleccionar filas por índice
                    int[] indices = new int[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        indices[i] = Integer.parseInt(parameters[i].trim());
                    }
                    // loc con todas las columnas: usamos select de todas las columnas
                    // pero como loc requiere una sola columna, hacemos la selección manualmente
                    result = selectRows(source, indices);
                    break;

                case 'c': // Seleccionar columnas por nombre
                    result = source.select(parameters);
                    break;

                case '?': // Filtrar por condición [columna, valor]
                    result = source.filter(parameters);
                    break;

                default:
                    lastOperationOk = false;
                    return;
            }

            variables.put(a, result);
            lastOperationOk = (result != null);
        }
        
        /**
     * Retorna un nuevo DataFrame con solo las filas indicadas por índice.
     *
     * @param source  DataFrame original
     * @param indices Índices de las filas a seleccionar
     * @return        Nuevo DataFrame con las filas seleccionadas o null si hay error
     */
    private DataFrame selectRows(DataFrame source, int[] indices) {
    
        if (source == null || indices == null) {
            return null;
        }
    
        String[][] originalData = source.getData();
        String[] columns = source.getColumns();
    
        String[][] newData = new String[indices.length][columns.length];
    
        for (int i = 0; i < indices.length; i++) {
    
            int rowIndex = indices[i];
    
            // Validar índice fuera de rango
            if (rowIndex < 0 || rowIndex >= originalData.length) {
                return null;
            }
    
            for (int j = 0; j < columns.length; j++) {
                newData[i][j] = originalData[rowIndex][j];
            }
        }
    
        return new DataFrame(newData, columns);
    }
      
    
    //Assigns the value of a binary operation to a variable
    // a = b op c
    //The operator characters are:  'r' concate by rows, 'c' concate by columns
    public void assignBinary(String a, String b, char op, String c){
    }
  
    
    /**
     * Retorna las primeras 'rows' filas del DataFrame almacenado en 'variable'.
     * @param variable Nombre de la variable
     * @param rows     Número de filas a mostrar
     * @return         Cadena formateada con la tabla, o null si no existe
     */
    public String head(String variable, int rows) {
        DataFrame df = getDF(variable);
        if (df == null) {
            lastOperationOk = false;
            return null;
        }
        lastOperationOk = true;
        return df.head(rows);
    }
    
    
    //If the last operation was successfully completed
    public boolean ok(){
        return lastOperationOk;
    }
    
}
    




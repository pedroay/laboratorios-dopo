public class DataFrame {
    private String[][] data;
    private String[] columns;

    // Constructor: filtra filas inválidas aplicando el invariante
    public DataFrame(String[][] data, String[] columns) {
        this.columns = columns;

        // Contar cuántas filas son válidas (longitud == número de columnas)
        int validCount = 0;
        for (String[] row : data) {
            if (row.length == columns.length) validCount++;
        }

        // Solo guardar las filas válidas
        this.data = new String[validCount][];
        int i = 0;
        for (String[] row : data) {
            if (row.length == columns.length) {
                this.data[i++] = row;
            }
        }
    }
    
    public String[] getColumns(){
        return columns;
    }
    
    public String[][] getData(){
        return data;
    }
    
    
    /**
     * shape: retorna {número de filas válidas, número de columnas}
     */ 
    public int[] shape() {
        return new int[]{data.length, columns.length};
    }

    public DataFrame loc(int[] rows, String column) {
        return null;
    }

        /**
     * Retorna un nuevo DataFrame con solo las columnas indicadas.
     *
     * @param values Nombres de las columnas a seleccionar
     * @return Nuevo DataFrame con las columnas seleccionadas
     *         o null si alguna columna no existe
     */
    public DataFrame select(String[] values) {
    
        if (values == null || values.length == 0) {
            return null;
        }
    
        // Arreglo para guardar los índices reales de las columnas
        int[] columnIndexes = new int[values.length];
    
        // Buscar cada columna solicitada
        for (int i = 0; i < values.length; i++) {
    
            boolean found = false;
    
            for (int j = 0; j < columns.length; j++) {
                if (columns[j].equals(values[i])) {
                    columnIndexes[i] = j;
                    found = true;
                    break;
                }
            }
    
            // Si no se encontró alguna columna → error
            if (!found) {
                return null;
            }
        }
    
        // Crear nueva matriz con las mismas filas pero menos columnas
        String[][] newData = new String[data.length][values.length];
    
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnIndexes.length; j++) {
                newData[i][j] = data[i][columnIndexes[j]];
            }
        }
    
        // Crear nuevo DataFrame con las columnas seleccionadas
        return new DataFrame(newData, values);
    }
    

    public DataFrame concat(DataFrame[] dfs, byte axis) {
        return null;
    }

    // The columns are aligned, separated by three spaces, and include the index.
    //     Nombre   Edad    Profesion
    // 0    Lucía     28    Ingeniero
    // 1   Carlos     35     Profesor
    // 2      Ana     42       Doctor
    // 3    Jorge     30   Arquitecto
    // 4    Elena     25    Diseñador
    public String head(int rows) {
        StringBuilder sb = new StringBuilder();
    
        // Imprimir columnas
        for (String col : columns) {
            sb.append(col).append("   ");
        }
        sb.append("\n");
    
        // Limitar número de filas
        int limit = Math.min(rows, data.length);
    
        for (int i = 0; i < limit; i++) {
            sb.append(i).append("   ");
            for (int j = 0; j < data[i].length; j++) {
                sb.append(data[i][j]).append("   ");
            }
            sb.append("\n");
        }
    
        return sb.toString();
    }
    /**
     * REVISA SI DATAFRAME ACTUAL CON EL DEL ARGUMENTO SON IGUALES
     */
    public boolean equals(DataFrame df) {
        if (df == null) return false;
        if (this.data.length != df.data.length) return false;
        if (this.columns.length != df.columns.length) return false;
        for (int i = 0; i < this.columns.length; i++) {
            if (!this.columns[i].equals(df.columns[i])) return false;
        }
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {
                if (!this.data[i][j].equals(df.data[i][j])) return false;
            }
        }
        return true;
    }

    public boolean equals(Object o) {
        if (o instanceof DataFrame) return equals((DataFrame) o);
        return false;
    }
    
    /**
 * Filtra las filas cuyo valor en una columna específica
 * sea igual al valor indicado.
 *
 * parameters[0] = nombre de la columna
 * parameters[1] = valor a comparar
 *
 * @param parameters [columna, valor]
 * @return Nuevo DataFrame filtrado o null si hay error
 */
public DataFrame filter(String[] parameters) {

    if (parameters == null || parameters.length != 2) {
        return null;
    }

    String columnName = parameters[0];
    String value = parameters[1];

    // Buscar índice de la columna
    int columnIndex = -1;
    for (int i = 0; i < columns.length; i++) {
        if (columns[i].equals(columnName)) {
            columnIndex = i;
            break;
        }
    }

    if (columnIndex == -1) {
        return null; // columna no existe
    }

    // Contar filas que cumplen condición
    int count = 0;
    for (int i = 0; i < data.length; i++) {
        if (data[i][columnIndex].equals(value)) {
            count++;
        }
    }

    // Crear nueva matriz
    String[][] newData = new String[count][columns.length];
    int newRow = 0;

    for (int i = 0; i < data.length; i++) {
        if (data[i][columnIndex].equals(value)) {
            for (int j = 0; j < columns.length; j++) {
                newData[newRow][j] = data[i][j];
            }
            newRow++;
        }
    }

    return new DataFrame(newData, columns);
}
}
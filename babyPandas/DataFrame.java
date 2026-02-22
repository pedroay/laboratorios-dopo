public class DataFrame {
    private String[][] data;
    private String[] columns;

    /**
     * crea el dataframe
     */
    public DataFrame(String[][] data, String[] columns) {
        this.columns = columns;
        int validCount = 0;
        for (String[] row : data) {
            if (row.length == columns.length) validCount++;
        }
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


     /**
     * Retorna un nuevo DataFrame con solo las columnas indicadas.
     */
    public DataFrame select(String[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        int[] columnIndexes = new int[values.length];
        for (int i = 0; i < values.length; i++) {   
            boolean found = false;   
            for (int j = 0; j < columns.length; j++) {
                if (columns[j].equals(values[i])) {
                    columnIndexes[i] = j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return null;
            }
        }
        String[][] newData = new String[data.length][values.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnIndexes.length; j++) {
                newData[i][j] = data[i][columnIndexes[j]];
            }
        }
        return new DataFrame(newData, values);
    }
    

        /**
     * Concatena este DataFrame con otros DataFrames.
     * axis = 0 → concatenar por filas
     * axis = 1 → concatenar por columnas
     */
    public DataFrame concat(DataFrame[] dfs, byte axis) {
        if (dfs == null || dfs.length == 0) {
            return null;
        }  
        DataFrame other = dfs[0];  
        if (axis == 0) {
            if (this.columns.length != other.columns.length) {
                return null;
            }   
            for (int i = 0; i < columns.length; i++) {
                if (!this.columns[i].equals(other.columns[i])) {
                    return null;
                }
            } 
            int totalRows = this.data.length + other.data.length;
            int cols = columns.length;    
            String[][] newData = new String[totalRows][cols];
            for (int i = 0; i < this.data.length; i++) {
                for (int j = 0; j < cols; j++) {
                    newData[i][j] = this.data[i][j];
                }
            }
            for (int i = 0; i < other.data.length; i++) {
                for (int j = 0; j < cols; j++) {
                    newData[i + this.data.length][j] = other.data[i][j];
                }
            }   
            return new DataFrame(newData, this.columns);
        }
    
        else if (axis == 1) {
            if (this.data.length != other.data.length) {
                return null;
            }   
            int rows = data.length;
            int totalCols = this.columns.length + other.columns.length;   
            String[][] newData = new String[rows][totalCols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < this.columns.length; j++) {
                    newData[i][j] = this.data[i][j];
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < other.columns.length; j++) {
                    newData[i][j + this.columns.length] = other.data[i][j];
                }
            }
            String[] newColumns = new String[totalCols];
            for (int i = 0; i < this.columns.length; i++) {
                newColumns[i] = this.columns[i];
            }
            for (int i = 0; i < other.columns.length; i++) {
                newColumns[i + this.columns.length] = other.columns[i];
            }   
            return new DataFrame(newData, newColumns);
        }   
        return null;
    }

    
    /**
     * Retorna las primeras  filas del DataFrame, incluyendo los nombres de las columnas.
     * el numeor de filas depende del argumento que se usa
     */
    public String head(int rows) {
        StringBuilder sb = new StringBuilder();
        for (String col : columns) {
            sb.append(col).append("   ");
        }
        sb.append("\n");
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
     */
    public DataFrame filter(String[] parameters) {
        if (parameters == null || parameters.length != 2) {
            return null;
        } 
        String columnName = parameters[0];
        String value = parameters[1];
        int columnIndex = -1;
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals(columnName)) {
                columnIndex = i;
                break;
            }
        } 
        if (columnIndex == -1) {
            return null; 
        }
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i][columnIndex].equals(value)) {
                count++;
            }
        }
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
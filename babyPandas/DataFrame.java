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

    /**
     * shape: retorna {número de filas válidas, número de columnas}
     */ 
    public int[] shape() {
        return new int[]{data.length, columns.length};
    }

    public DataFrame loc(int[] rows, String column) {
        return null;
    }

    public DataFrame select(String[] values) {
        return null;
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
}
    import java.util.Stack;
    import java.util.ArrayList;
    
    /**
     * Write a description of class Tower here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class Tower
    {
        private int width;
        private int height;
        private int minHeight;
        private int maxHeight;
        private boolean isVisible;
        private Stack<Cup> cups;
        private Stack<Lid> lids;
        private boolean isOK;
        private Cup top;
        
        /**
         * Constructor for objects of class Tower
         */
        public Tower(int nwidth, int nmaxHeight)
        {
            width = nwidth;
            maxHeight = nmaxHeight;
            isVisible = false;
            cups = new Stack<Cup>();
            lids = new Stack<Lid>();
            isOK = true;
        }
        
        /**
         * Agrega una taza al tope de la torre
         */
        public void pushCup(int i){
            for(Cup c : cups){
                if(c.getNumber() == i){
                    isOK = false;
                    return;
                }
            }

            Cup newCup = new Cup(i);
            if(top == null){
                top = newCup;
                cups.push(newCup);
                Lid lid = newCup.getCover();
                lids.push(lid);
                isOK = true;
                return;
            }
            else{
                int sizeTop = top.getHeight();
                int sizeNewCup = newCup.getHeight();
                boolean topCover = top.isCovered();
                int posyTop =top.getPosy();
                int posyNewCup = newCup.getPosy();
                if (sizeTop > sizeNewCup && !topCover){
                    setInside(top,newCup);
                    cups.push(newCup);
                    setNewTop(this.top,newCup);
                }
                else if(sizeTop < sizeNewCup || topCover){
                    setAbove(top,newCup);
                    cups.push(newCup);
                    setNewTop(this.top,newCup);
                }
            }
            }

        private void setInside(Cup base,Cup apilar){
            Cup insideBase = base.getCupInside();
            int posxApilar = apilar.getPosx();
            int posyBase = base.getPosy();
            int baseHeight = base.getHeight();
            int apilarHeight = apilar.getHeight();
            if(insideBase == null){
                apilar.setPosition(posxApilar,posyBase+baseHeight-10-apilarHeight);
                base.setCupInside(apilar);
            }
            else{
                int insideBaseHeight = insideBase.getHeight();
                boolean insideBaseCover = insideBase.isCovered();
                if(insideBaseHeight > apilarHeight && !insideBaseCover){
                    setInside(insideBase,apilar);    
                }
                else if(insideBaseHeight <= apilarHeight || insideBaseCover){
                    setAbove(insideBase,apilar);
                }
            }
        }
        
        private void setNewTop(Cup Top,Cup newCup){
            int posyTop = top.getPosy();
            int posyNewCup = newCup.getPosy();
            if (posyTop > posyNewCup){
                top = newCup;
            }
        }
        
        private void setAbove(Cup base, Cup apilar){
           base.setCupAbove(apilar);
           int posxApilar = apilar.getPosx();
           int posyBase = base.getPosy();
           int apilarHeight = apilar.getHeight();
           apilar.setPosition(posxApilar,posyBase-apilarHeight);
           
        }
        private void towerHeight(Cup nCup){
            for (Cup c:cups){
                int cHeight = c.getHeight();
                int nCHeight = nCup.getHeight();
                if (cHeight > nCHeight){
                    String cCover =c.getState();
                    
                }
            }
        }
     
        /**
         * Remueve y retorna la taza del tope
         */
        public Cup popCup(){   
            Cup tope = this.top;
            this.top = null;
            top = cups.get(0);
            cups.remove(tope);
            for( Cup c:cups){
                int posyC=c.getPosy();
                int posyTop = top.getPosy();
                setNewTop(top,c);
                
            }
            tope.makeInvisible();
            return tope;
        }
        
        /**
         * Remueve una taza específica por número
         */
        public void removeCup(int i){
            Stack<Cup> temp = new Stack();
            Cup rCup = null;
            for (Cup c:cups){
                int cI = c.getNumber();
                if(cI == i) {
                   rCup = c;
                   break;
                }
            }
            cups.remove(rCup);
            for (Cup c: cups){
                c.setCupInside(null);
                c.setCupAbove(null);
                temp.push(c);
            }
            cups.clear();
            top = null;
            for(Cup t:temp){
                int tI = t.getNumber();
                pushCup(tI);
            }
        }
        
        /**
         * Agrega una tapa al tope de la torre
         */
        public void pushLid(int i){
            int topNumber = top.getNumber();
            if(topNumber == i){
                Lid topLid = top.getCover();
                top.setState("Covered");
                topLid.makeVisible();
            }
        }
        
        /**
         * Remueve la tapa del tope
         */
        public void popLid(){
            if(top.isCovered()){
                Lid topLid = top.getCover();
                topLid.makeInvisible();
                top.setState("noCovered");
            }
            return;
        }
    
        
        /**
         * Remueve una tapa del stack
         */
        public void removeLid(int i){
            Cup rLidCup = null;
            for(Cup c : cups){
                int cI = c.getNumber();
                if(cI == i){
                    rLidCup = c;
                    break;
                }
            }
            Lid rLid = rLidCup.getCover();
        }
        
        /**
         * Ordena la torre de mayor a menor altura
         */
        public void orderTower()
        {
            ArrayList<Cup> temp = new ArrayList<Cup>(cups);
            for (int i = 0; i < temp.size(); i++) {
                for (int j = 0; j < temp.size() - 1; j++) {
                    if (temp.get(j).getNumber() < temp.get(j + 1).getNumber()) {
                        Cup aux = temp.get(j);
                        temp.set(j, temp.get(j + 1));
                        temp.set(j + 1, aux);
                    }
                }
            }
            cups.clear();
            for (Cup c : temp) {
                cups.push(c);
            }
            System.out.println(cups);
            isOK = true;
            }
        
        /**
         * Invierte el orden de la torre
         */
        public void reverseTower()
        {
            Stack<Cup> temp = new Stack<Cup>();
            while (!cups.isEmpty()) {
                temp.push(cups.pop());
            }
            cups = temp;
            isOK = true;
        }
        
        /**
         * Retorna la altura total de elementos apilados
         */
        public int getHeight()
        {
            int totalHeight = 0;
            for (Cup c : cups) {
                totalHeight += c.getHeight();
            }        
            return totalHeight;
        }
        
        /**
         * Retorna array con números de tazas tapadas
         */
        public int[] lidedCups()
        {
            ArrayList<Integer> covered = new ArrayList<Integer>();
            
            for (Cup c : cups) {
                if (c.isCovered()) {
                    covered.add(c.getNumber());
                }
            }
            int[] result = new int[covered.size()];
            for (int i = 0; i < covered.size(); i++) {
                result[i] = covered.get(i);
            }
            return result;
        }
        
        /**
         * Retorna matriz con tipo y número de elementos
         */
        public String[][] stackingItems()
        {
            int totalElements = cups.size() + lids.size();
            String[][] result = new String[totalElements][2];
            
            // Convertir stack a lista para iterar
            ArrayList<Cup> cupList = new ArrayList<Cup>(cups);
            ArrayList<Lid> lidList = new ArrayList<Lid>(lids);
            
            int index = 0;
            
            // Agregar tazas
            for (Cup c : cupList) {
                result[index][0] = "cup";
                result[index][1] = String.valueOf(c.getNumber());
                index++;
            }
            
            // Agregar tapas
            for (Lid l : lidList) {
                result[index][0] = "lid";
                result[index][1] = String.valueOf(l.getHeight());
                index++;
            }
            
            return result;
        }
        
        /**
         * Hace visible la torre
         */
        public void makeVisible()
        {   for(Cup c:cups){
                c.makeVisible();
            }
            isVisible = true;
        }
        
        /**
         * Hace invisible la torre
         */
        public void makeInvisible()
        {
            for(Cup c:cups){
            c.makeInvisible();
            isVisible = false;
        }
        }
        
        /**
         * Verifica si la torre es visible
         */
        public boolean isVisible(){   
            return isVisible;
            
        }
        
        /**
         * Termina el simulador
         */
        public void exit()
        {
            cups.clear();
            lids.clear();
            isVisible = false;
        }
        
        /**
         * Verifica si la última operación fue exitosa
         */
        public boolean isOk()
        {
            return isOK;
        }
        
        /**
         * Retorna el tamaño del stack de tazas
         */
        public int getCupsSize()
        {
            return cups.size();
        }
        
        /**
         * Retorna el tamaño del stack de tapas
         */
        public int getLidsSize()
        {
            return lids.size();
        }
        
        public void drawRule(){
            for (int i=0;i<=maxHeight;i++){
                Rectangle r= new Rectangle();
                r.changeSize(2,10);
                r.setP(i*10,0);
                r.makeVisible();
            }
        }
    }
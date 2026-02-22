    import java.util.Stack;
    import java.util.ArrayList;
    import javax.swing.JOptionPane;

    
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
                    if(isVisible){
                        JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de pushCup");
                    }
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
                isOK = true;
            }
            }
            
        /**
         * agrega una copa dentro de otra copa
         */
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
            isOK = true;
        }
        
        /**
         * avalua si toca cambiar el top y si si lo cambia
         */
        private void setNewTop(Cup Top,Cup newCup){
            int posyTop = top.getPosy();
            int posyNewCup = newCup.getPosy();
            if (posyTop > posyNewCup){
                top = newCup;
            }
        }
        
        /**
         * coloca una copa encima de otra
         */
        private void setAbove(Cup base, Cup apilar){
           base.setCupAbove(apilar);
           int posxApilar = apilar.getPosx();
           int posyBase = base.getPosy();
           int apilarHeight = apilar.getHeight();
           apilar.setPosition(posxApilar,posyBase-apilarHeight);
           
        }
     
        /**
         * Remueve y retorna la taza del tope
         */
        public Cup popCup(){ 
            if(!cups.isEmpty()){
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
                isOK = true;
                return tope;
            }
            if(isVisible()){
                JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de popCup");
            }
            isOK = false;
            return null;
            }
        
        /**
         * Remueve una taza específica por número
         */
        public void removeCup(int i){
            if(!cups.isEmpty()){
                Stack<Cup> temp = new Stack();
                Cup rCup = null;
                for (Cup c:cups){
                    int cI = c.getNumber();
                    if(cI == i) {
                       rCup = c;
                       break;
                    }
                }
                if(rCup == null){
                   if(isVisible())JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de removeCup");
                   isOK = false;
                   return;
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
                return;
            }
            if(isVisible()){
                JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de removeCup");
 
            }
            isOK = false;
        }
        
        /**
         * Agrega una tapa al tope de la torre
         */
        public void pushLid(int i){
            if(top != null){
                int topNumber = top.getNumber();
                if(topNumber == i){
                    Lid topLid = top.getCover();
                    top.setState("Covered");
                    topLid.makeVisible();
                    isOK = true;
                    return;
                }
            }
            if(isVisible()){
                JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de pushLid");
 
            }
            isOK = false;
        }
        
        /**
         * Remueve la tapa del tope
         */
        public void popLid(){
            if (top!= null){
                if(top.isCovered()){
                    Lid topLid = top.getCover();
                    topLid.makeInvisible();
                    top.setState("noCovered");
                    isOK = true;
                    return;
                }
                if(isVisible()){
                    JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de popLid");
 
                }
                isOK = false;
            }
            if(isVisible()){
                    JOptionPane.showMessageDialog(null, "No se pudo hacer la acción de popLid");
 
                }
                isOK = false;
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
            rLidCup.setState("covered");
        }
        
        /**
         * Ordena la torre de mayor a menor altura
         */
        public void orderTower(){
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
            top = null;
            for (Cup c : temp) {
                c.setCupInside(null);
                c.setCupAbove(null);
                int cI = c.getNumber();
                pushCup(cI);
            }
            makeVisible();
            isOK = true;
            }
        
        /**
         * Invierte el orden de la torre
         */
        public void reverseTower()
        {
            Stack<Cup> temp = new Stack<Cup>();
            top = null;
            while (!cups.isEmpty()) {
                Cup c = cups.pop();
                c.setCupInside(null);
                c.setCupAbove(null);
                temp.push(c);
            }
            for(Cup c: temp){
                int cI = c.getNumber();
                pushCup(cI);
            }
            isOK = true;
        }
        
        /**
         * Retorna la altura total de elementos apilados
         */
        public int height(){
            int totalHeight;
                if(top != null){
                    totalHeight= 300- top.getPosy();
                    isOK = true;
                    return totalHeight;
                
            }
            isOK = true;
            return totalHeight = 0;
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
        public String[][] stackingItems() {
             ArrayList<String[]> temp = new ArrayList<>();
            for (Cup c : cups) {
        
                temp.add(new String[]{
                    "cup",
                    String.valueOf(c.getNumber())
                });
        
                if (c.isCovered()) {
                    Lid lid = c.getCover();
                    temp.add(new String[]{
                        "lid",
                        String.valueOf(lid.getHeight())
                    });
                }
            }
            String[][] result = new String[temp.size()][2];
        
            for (int i = 0; i < temp.size(); i++) {
                result[i] = temp.get(i);
            }
        
            return result;
        }

        
        /**
         * Hace visible la torre
         */
        public void makeVisible()
        {   for(Cup c:cups){
                int cPosy = c.getPosy();
                if (cPosy >= 0){
                c.makeVisible();
            }
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
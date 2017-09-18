package Huffman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileAdministrator {
    private String textToCompress;
    private ArrayList<charFreq> chars;
    private treeBin codeTree;
    private String cStr;
    private int originalLen;
    
    private String fileName;
    private String fileFormat;
    private File file =  null;
    
    
    private final String parseChar1 = "" + (char)256;
    private final String parseChar2 = "" + (char)257;

    public FileAdministrator() {
        this.codeTree = new treeBin();
    }
    
    public FileAdministrator(String path) {
        file = new File(path);
        this.codeTree = new treeBin();
    }
    
    public FileAdministrator(String path, String name, String format) {
        file = new File(path + "/" + name + "." + format);
        this.codeTree = new treeBin();
    }
    
    public FileAdministrator(File path) {
        file = path;
        this.codeTree = new treeBin();
    }

    public treeBin getCodeTree() {
        return codeTree;
    }

    public void setCodeTree(treeBin codeTree) {
        this.codeTree = codeTree;
    }

    public String getcStr() {
        return cStr;
    }

    public void setcStr(String cStr) {
        this.cStr = cStr;
    }

    public int getOriginalLen() {
        return originalLen;
    }

    public void setOriginalLen(int originalLen) {
        this.originalLen = originalLen;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public void setFile(String path) {
        this.file = new File(path);
    }
    
    public void setFile(String path, String name, String format) {
        this.file = new File(path + "/" + name + "." + format);
    }
    
    public String getTextToCompress(){
        return textToCompress;
    }
    
    public String getDecompressedText(){
        return decompress(codeTree, StrToBin(cStr), originalLen);
    }
    
    //to compress
    //n°1
    public void setTextToCompress(String str){
        //Save original text
        if(!str.isEmpty()){
            textToCompress = str;
            chars = createSymbolsChart(str);

            //Get sorted list of char frequencies
            ArrayList<charFreq> sortedList = new ArrayList();
            getFromHeap(heapSort(chars), sortedList);

            //Generate codeTree
            ArrayList<treeBin> treeList = createTreeList(sortedList);
            codeTree = createTrees(treeList).get(0);

            //Generate binary of text
            String cText = generateCompressedBinary(codeTree, str);

            //Fill binary text, save originalLen
            originalLen = (short)cText.length();
            cText = fillBinary(cText, originalLen);

            //Convert binary text into string
            cStr = BinToStr(cText);

            //sort char frequencies
            sortedList = new ArrayList();
            getFromHeap(heapSort(chars), sortedList);
            chars = sortedList;
        }
    }
    
    //n°2
    public ArrayList<charFreq> createSymbolsChart(String str){
        ArrayList<charFreq> chars = new ArrayList();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            boolean b = false;
            int index = -1;
            for(int j = 0; j < chars.size(); j++){
                if(chars.get(j).getChar() == c){    
                    b = true;
                    index = j;
                }
            }
            if(b){
                chars.get(index).addFreq();
            }else{
                chars.add(new charFreq(c));
            }
        }
        return chars;
    }
    
    //n°3
    public treeBin heapSort(ArrayList<charFreq> list){
        treeBin heap = new treeBin(list.get(0), list.get(0).getFreq());
        for(int i = 1; i < list.size(); i++){
            //T(log n)
            heap.insertNode(new treeBin(list.get(i), list.get(i).getFreq()));
        }
        return heap;
    }
    
    //n°4
    public void getFromHeap(treeBin heap, ArrayList sortedList)
    {
        if(heap.getLeft() != null)
            getFromHeap(heap.getLeft(), sortedList);
        
        sortedList.add(heap.getValue());
        
        if(heap.getRight() != null)
            getFromHeap(heap.getRight(), sortedList);
    }
    
    //n°5
    public ArrayList<treeBin> createTreeList(ArrayList<charFreq> chars){
        ArrayList<treeBin> treeList = new ArrayList();
        while(!chars.isEmpty()){
            charFreq firstLeaf = chars.get(0);
            chars.remove(0);
            
            charFreq secondLeaf = null;
            if(chars.size() > 0){
                secondLeaf = chars.get(0);
                chars.remove(0);
            }
            
            //Create trees
            int label = firstLeaf.getFreq() + ((secondLeaf == null)?0:secondLeaf.getFreq());
            treeList.add(
                new treeBin(
                        new treeBin(firstLeaf), 
                        new treeBin(secondLeaf), 
                        null, 
                        label
                )
            );//End of new treeBin
        }
        return treeList;
    }
    
    //n°6
    public ArrayList<treeBin> createTrees(ArrayList<treeBin> treeList){
        if(treeList.size() == 1)
            return treeList;
        
        ArrayList<treeBin> sortedList = new ArrayList();
        getFromHeap(heapSortTree(treeList), sortedList);
        
        ArrayList<treeBin> newTreeList = new ArrayList();
        while(!sortedList.isEmpty()){
            treeBin firstLeaf = sortedList.get(0);
            sortedList.remove(0);
            
            treeBin secondLeaf = null;
            if(sortedList.size() > 0){
                secondLeaf = sortedList.get(0);
                sortedList.remove(0);
            }
            
            //Create trees
            int label = firstLeaf.getLabel() + ((secondLeaf == null)?0:secondLeaf.getLabel());
            newTreeList.add(
                new treeBin(
                        firstLeaf, 
                        secondLeaf, 
                        null, 
                        label
                )
            );//End of new treeBin
        }
        return createTrees(newTreeList);
    }
    
    //n°7
    public treeBin heapSortTree(ArrayList<treeBin> list){
        treeBin heap = new treeBin(list.get(0), list.get(0).getLabel());
        for(int i = 1; i < list.size(); i++){
            heap.insertNode(new treeBin(list.get(i), list.get(i).getLabel()));
        }
        return heap;
    }
    
    //n°8
    public String generateCompressedBinary(treeBin codeTree, String originalText){
        String cText = "";
        
        for(int i = 0; i < originalText.length(); i++){
            cText += getFromCodeTree(codeTree, originalText.charAt(i),"");
        }
        
        return cText;
    }
    
    //n°9
    public String getFromCodeTree(treeBin codeTree, char search, String code){
        if(codeTree.getLeft() != null){
            int len = code.length();
            code = getFromCodeTree(codeTree.getLeft(), search, code + "0");
            if(len < code.length())
                return code;
        }
        
        if(codeTree.getRight() != null){
            int len = code.length();
            code = getFromCodeTree(codeTree.getRight(), search, code + "1");
            if(len < code.length())
                return code;
        }
        
        if(codeTree.getValue() != null && search == ((charFreq)codeTree.getValue()).getChar()){
            return code;
        }else{
            return code.substring(0, code.length() - 1);
        }
    }
    
    //n°10
    public String fillBinary(String cText, int originalLen){
        int delta = (originalLen % 8 == 0)?0:(8 - originalLen % 8);
        
        for (int i = 0; i < delta; i++) {
            cText += "0";
        }
        return cText;
    }
    
    //n°11
    public String BinToStr(String bin){
        String str = "";
        
        for(int i = 0; i < bin.length(); i += 8){
            String part = bin.substring(i, i + 8);
            int bytes = 0;
            for (int j = 0; j < part.length(); j++) {
                if(part.charAt(j) == '0'){
                    bytes <<= 1;
                }else{
                    bytes <<= 1;
                    bytes++;
                }
            }
            char c = (char) bytes;
            str += String.valueOf(c);
        }
        
        return str;
    }
    
    //n°12
    public String StrToBin(String str){
        String bin = "";
        for(int i = 0; i < str.length(); i++){
            byte c = (byte)str.charAt(i);
            String tempBin = "";
            for (int j = 0; j < 8; j++) {
                if(c % 2 == 0){
                    tempBin = "0" + tempBin;
                }else{
                    tempBin = "1" + tempBin;
                }
                c >>= 1;
            }
            bin += tempBin;
        }
        
        return bin;
    }
    
    //n°13
    public String decompress(treeBin treeCode, String cText, int originalLen){
        String text = "";
        treeBin head = treeCode;
        for(int i = 0; i < originalLen; i++){
            if(cText.charAt(i) == '0'){
                head = head.getLeft();
            }else{
                head = head.getRight();
            }
            if(!head.hasChildren()){
                text += ((charFreq)head.getValue()).getChar();
                head = treeCode;
            }
        }
        return text;
    }
    
    //to save/load
    /*public void save(){
        FileWriter fw= null;
        BufferedWriter bw = null;
        
        try {
            fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);
            bw.write(cStr + ";" + originalLen + ";");
            for (charFreq aChar : chars) {
                bw.write(aChar.getChar() + "," + aChar.getFreq() + ",");
            }
            bw.flush();
        } catch (Exception e) {
        }finally{
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
            }
        }
    }*/
    
    public void save(){
        FileWriter fw= null;
        Writer bw = null;
        
        try {
            fw = new FileWriter(file, false);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
            bw.write(cStr + parseChar1 + originalLen + parseChar1);
            for (charFreq aChar : chars) {
                bw.write(aChar.getChar() + parseChar2 + aChar.getFreq() + parseChar2);
            }
            bw.flush();
        } catch (Exception e) {
        }finally{
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void load(){
        Scanner sc = null;
        codeTree = new treeBin();
        cStr = "";
        originalLen = 0;
        try {
            sc = new Scanner(file);
            sc.useDelimiter(String.valueOf(parseChar1));
            
            //Invert Order
            cStr = sc.next();
            originalLen = sc.nextInt();
            
            //Cargar tabla de frequencias
            String charList = sc.next();
            sc = new Scanner(charList);
            sc.useDelimiter(String.valueOf(parseChar2));
            chars = new ArrayList();
            
            while(sc.hasNext()){
                chars.add(new charFreq(sc.next().charAt(0), sc.nextInt()));
            }
            codeTree = createTrees(createTreeList(chars)).get(0);
            
        } catch (Exception e) {
        }finally{
            try {
                sc.close();
            } catch (Exception e) {
            }
        }
    }
    
    /*public void cargarArchivoBin(){
        try {
            codeTree = new treeBin();
            //
            File file = new File("./compressedText/newFile.bin");
            if(this.file.exists()){
                FileInputStream entrada = new FileInputStream(file);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {//End of file Exception
                    codeTree = (treeBin)(objeto.readObject());
                } catch (EOFException e) {
                } finally {
                    objeto.close();
                    entrada.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    /*public void escribirArchivoBin(){
        FileOutputStream fos = null;
        ObjectOutputStream bos = null;
        //
        File file = new File("./compressedText/newFile.bin");
        try {
            fos = new FileOutputStream(file);
            bos = new ObjectOutputStream(fos);
            bos.writeObject(codeTree);
            bos.flush();
        } catch (Exception e) {
        } finally {
            try {
                bos.close();
                fos.close();
            } catch (Exception e) {
            }
        }
    }*/
}

class charFreq implements Serializable{
    private char chara;
    private int freq;
    
    public charFreq(char chara) {
        this.chara = chara;
        this.freq = 1;
    }

    public charFreq(char chara, int freq) {
        this.chara = chara;
        this.freq = freq;
    }
    
    public char getChar() {
        return chara;
    }

    public void setChar(char chara) {
        this.chara = chara;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
    
    public void addFreq() {
        this.freq++;
    }

    @Override
    public String toString() {
        return "charF[" + "char=" + chara + ", freq=" + freq + ']';
    }
}

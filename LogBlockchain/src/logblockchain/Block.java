package logblockchain;

import java.util.Date;

public class Block {
    public String hash;
    public String hashAnterior;
    private String dadosLog;
    private long timeStamp;
    private int id;

    public Block(String data, String previousHash) {
        this.dadosLog = data;
        this.hashAnterior = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calcularHash(); 
    }

    public String calcularHash() {
        String hashCalculado = StringUtil.aplicarSha256(hashAnterior
                + Long.toString(timeStamp)
                + Integer.toString(id)
                + dadosLog
        );
        return hashCalculado;
    }

    //Increases id value until hash target is reached.
    public void mineBlock(int dificuldade) {
        String target = StringUtil.getDificuldadeString(dificuldade); //Create a string with difficulty * "0" 
        while (!hash.substring(0, dificuldade).equals(target)) {
            id++;
            hash = calcularHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logblockchain;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class LogBlockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int dificuldade = 5;

    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:

        System.out.println("Trying to Mine block 1... ");
        addBlock(new Block("Hi im the first block", "0"));

        System.out.println("Trying to Mine block 2... ");
        addBlock(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("Trying to Mine block 3... ");
        addBlock(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("\nBlockchain is Valid: " + isValid());

        String blockchainJson = StringUtil.getJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }

    public static Boolean isValid() {
        Block blockAtual;
        Block blockAnterior;
        String hashTarget = new String(new char[dificuldade]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            blockAtual = blockchain.get(i);
            blockAnterior = blockchain.get(i - 1);
            
            if (!blockAtual.hash.equals(blockAtual.calcularHash())) {
                System.out.println("Hashes atuais não são iguais.");
                return false;
            }
            
            if (!blockAnterior.hash.equals(blockAtual.hashAnterior)) {
                System.out.println("Hashes anteriores não iguais");
                return false;
            }
            
            if (!blockAtual.hash.substring(0, dificuldade).equals(hashTarget)) {
                System.out.println("Este bloco não foi incluido");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block novo) {
        novo.mineBlock(dificuldade);
        blockchain.add(novo);
    }
}

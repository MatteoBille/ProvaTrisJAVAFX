package com.example.demo;

import java.util.Arrays;

public class Partita {
    public char[][] tabella = new char[3][3];
    char giocatore1 = 'X';
    char giocatore2 = 'O';
    char ultimaMossa = ' ';
    char turno = ' ';

    Partita() {
        turno = 'X';
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               tabella[i][j]='.';
           }
       }
    }

    public void giocaMossa(int i, int j) {
        tabella[i][j] = turno;
        if (turno == giocatore1) {
            ultimaMossa = giocatore1;
            turno = giocatore2;
        } else {
            ultimaMossa = giocatore2;
            turno = giocatore1;
        }
    }


    public char getValoreCasella(int i, int j) {
        return tabella[i][j];
    }

    public String checkVittoria() {
        for (int i = 0; i < 3; i++) {
            System.out.println(tabella[i][0]+","+tabella[i][1]+","+tabella[i][2]);
            if (((tabella[i][0] == tabella[i][1]) && (tabella[i][1] == tabella[i][2])) && tabella[i][0]!='.') {
                System.out.println("vittoria di " + ultimaMossa);
                return "R," + i;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (((tabella[0][i] == tabella[1][i]) && (tabella[1][i] == tabella[2][i]))&& tabella[0][i]!='.') {
                System.out.println(tabella[0][i]+","+tabella[1][i]+","+tabella[2][i]);
                System.out.println("vittoria di " + ultimaMossa);
                return "C," + i;
            }
        }

        if (((tabella[0][0] == tabella[1][1] )&& (tabella[1][1] == tabella[2][2])) && tabella[0][0]!='.' ) {
            System.out.println(tabella[0][0]+","+tabella[1][1]+","+tabella[2][2]);
            System.out.println("vittoria di " + ultimaMossa);
            return "D,1";
        }

        if (((tabella[2][0] == tabella[1][1]) && (tabella[1][1] == tabella[0][2]))&& tabella[2][0]!='.') {
            System.out.println(tabella[2][0]+","+tabella[1][1]+","+tabella[0][2]);
            System.out.println("vittoria di " + ultimaMossa);
            return "D,2";
        }
        return null;
    }
}

public class Evento {

    
        int idEvento;
        int idorganizador;
        int idLocal;
        String data;
        String descricao;
        int vagas;
    
    
        public Evento (int idEvento, int idorganizador, int idLocal, String data, String descricao, int vagas){
            this.idEvento = idEvento;
            this.idorganizador = idorganizador;
            this.idLocal = idLocal;
            this.data = data;
            this.descricao = descricao;
            this.vagas  = vagas;
        }
    
        public String toString() {
            return "IdEvento: " + this.idEvento
                + "idorganizador:" + this.idorganizador
                + "idLocal" + this.idLocal
                + "data" + this.data
                + "descricao" + this.descricao
                + "vagas" + this.vagas;
        }
}


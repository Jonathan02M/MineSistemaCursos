public class Local {
    
        int idLocal;
        String descricao;
        int vagas;
        int eventos_reservados;
    
    
        public Local (int idLocal, String descricao, int vagas, int eventos_reservados){
            this.idLocal = idLocal;
            this.descricao = descricao;
            this.vagas = vagas;
            this.eventos_reservados = eventos_reservados;
        }
    
        public String toString() {
            return "Id: " + this.idLocal
                + "descricao: " + this.descricao
                + "vagas: " + this.vagas
                + "evento_reservados: " + this.eventos_reservados;
    
        }
 }
    

/*  1- Fazer conexão HTTP(Protocolo de comunicação) e buscar top 250 filmes
    2- Pegar dados que interessam(Titulo, poster e avaliação)
    3- Exibir e manipular os dados como queremos
*/
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App{
    public static void main(String[] args) throws Exception{

        //1-Conexão Http
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response =  client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //Imprime tudo o que está na API - System.out.println(body); 

        //2- Filtrando informações
        var parser =  new JsonParser();
              //<Valor chave, valor tipo associado>
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        //3- exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\n\u001b[41m\u001b[37m \u001b[1m Titulo: \u001b[m " + filme.get("title"));
            System.out.println("\u001b[41m\u001b[37m \u001b[1m Imagem: \u001b[m " + filme.get("image"));
            System.out.println("\u001b[41m\u001b[37m \u001b[1m Nota:   \u001b[m " + filme.get("imDbRating"));
            //Pega a classificação em decimal 
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            //Transforma em inteiro
            int numCoracoes = (int) classificacao;
            for(int i = 1; i <= numCoracoes; i++){
                System.out.print("❤️ ");
            }
            System.out.println();
        }
    }
}

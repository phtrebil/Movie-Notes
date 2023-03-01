<h1 align="center"> Movie Notes </h1>

Aplicativo que faz busca de filmes e mostra detalhes.

App consome uma api Web para realizar a busca de filmes por meio de seu título. Após realizada a busca, é possível clicar na caixa do item para abrir a tela de detalhes.
O app ainda não está finalizado. Pretende-se deixar que o usuário escolha o filme de sua preferência, faça anotações pessoais sobre o filme e deixe salvas essas informações,
localmente no aparelho. Também se tem problemas com os endereços das imagens disponibilizadas pela API, tendo em vista que não é possível carregá-las.
Por enquanto, o app apresenta duas telas.

<h4 align="center"> 
:construction: Projeto em construção :construction:
</h4>

<h5>
Tela de Busca de Filmes
</h5>

<h5 align="center">
<img src = "https://github.com/phtrebil/Movie-Notes/blob/main/Captura%20de%20tela%202023-02-28%20211645.jpg"
width="300px"/>
</h5>

<h5>
Implementado
</h5>
    
- Lista de filmes feita com recyclerView.
- Busca na API feita com Retrofit.
- Barra de pesquisa para buscar filmes.

<h5>
A Implementar
</h5>

-  Barra de pesquisa precisa responder click em todo o corpo (no momento, só aceita click na lupa).
-  Carregar imagem do poster dos filmes

<h5>
Tela de Detalhes
</h5>

<h5 align="center">
<img src = "https://github.com/phtrebil/Movie-Notes/blob/main/Captura%20de%20tela%202023-02-28%20211757.jpg"
width="300px"/>
</h5>

<h5>
Implementado
</h5>
    
- Receber dados da Activity de Lista de Filmes para inserir informações na lista de detalhes.

<h5>
A Implementar
</h5>

-  Carregar imagem do poster do filme.
-  Fazer o chainConstraint entre os elementos de textview para não encavalar o texto sobre texto.


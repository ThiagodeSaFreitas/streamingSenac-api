$(document).ready(function() {
    function carregarFilmes(){
        $.ajax({
            url : "http://localhost:8080/filme",
            method: 'GET',
            success: function (data){
                $('#tabelaFilmes tbody').empty();
                console.log("retorno", data);
                for (let i = 0; i < data.length; i++) {

                    let filme = data[i];
                    let btnDelete = $('<button>')
                    .addClass('btn btn-danger')
                    .text('Excluir')
                    .click(function(){
                        deletarFilme($(this).parent().parent().attr('data-id'));
                    });

                    let btnUpdate = $('<button>')
                    .addClass('btn btn-secondary')
                    .text('Atualizar')
                    .click(function(){
                        carregarFilme(filme)
                        
                    });

                    let id = $('<td>').text(filme.id)
                    let titulo = $('<td>').text(filme.titulo)
                    let sinopse = $('<td>').text(filme.sinopse)
                    let genero = $('<td>').text(filme.genero)
                    let lancamento = $('<td>').text(filme.lancamento)
                    let Btns = $('<td>').append(btnDelete).append(" ").append(btnUpdate);

                    let tr = $('<tr>')
                        .attr('data-id', filme.id)
                        .append(id)
                        .append(titulo)
                        .append(sinopse)
                        .append(genero)
                        .append(lancamento)
                        .append(Btns);

                    $('#tabelaFilmes tbody').append(tr);
                    
                }
            },
            error: function () { 
                alert('Não foi possível carregar os Filmes da API.'); 
            }
        })
    }

    function carregarFilme(filme){
        $('#id').val(filme.id);
        $('#titulo').val(filme.titulo);
        $('#sinopse').val(filme.sinopse);
        $('#genero').val(filme.genero);
        $('#lancamento').val(filme.lancamento);

        $('#btnInserirAtualizar').text("Salvar")
    }

    $('#formCreateFilme').submit(function(event){
        event.preventDefault();

        let id = $('#id').val();

        let titulo = $('#titulo').val();
        if(!titulo){
            alert('Título não preenchido corretamente');
            return;
        }

        let genero = $('#genero').val();
        if(!genero){
            alert('Gênero não preenchido corretamente');
            return;
        }

        let sinopse = $('#sinopse').val();
        if(!sinopse){
            alert('Sinópse não preenchido corretamente');
            return;
        }

        let lancamento = $('#lancamento').val();
        if(!lancamento){
            alert('Data de lançamento não preenchido corretamente');
            return;
        }

        let filme = {
            id: id,
            titulo: titulo,
            genero: genero,
            sinopse: sinopse,
            lancamento: lancamento
        }

        if (filme.id) {
            updateFilme(filme);
            
        } else {
            createFilme(filme);
        }

        
    })

    function createFilme(filme){
        $.ajax({
            url: "http://localhost:8080/filme",
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(filme),
            success: function (data) {
                
                $('#titulo').val('');
                $('#genero').val('');
                $('#sinopse').val('');
                $('#lancamento').val('');


                alert("Cadastro efeito com sucesso!");
                carregarFilmes();

              },
              error: function () {
                alert("Não foi possivel cadastrar o filme.");
              }
        })
    }

    function updateFilme(filme){
        $.ajax({
            url: "http://localhost:8080/filme/" + filme.id,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(filme),
            success: function (data) {
                
                $('#titulo').val('');
                $('#genero').val('');
                $('#sinopse').val('');
                $('#lancamento').val('');


                alert("Atualização feita com sucesso!");
                carregarFilmes();

              },
              error: function () {
                alert("Não foi possivel atualizar o filme.");
              }
        })
    }


    function deletarFilme(id) {
        //alert(id)
      $.ajax({
        url: "http://localhost:8080/filme/" + id,
        method: "DELETE",
        success: function (data) {
          alert("Filme deletado na API!!!");
          carregarFilmes();
        },
        error: function () {
          alert("Não foi possivel deletar o filme selecionado.");
        },
      });
    }
    carregarFilmes();
})


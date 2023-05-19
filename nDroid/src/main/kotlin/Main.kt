import java.io.File
import java.io.FileReader
import java.io.FileWriter

val funcionarios = mutableListOf<Funcionario>()

fun carregar_file(file: File): Int {
    if(file.exists()) {
        println("\nJá existe um arquivo dos funcionáriosm cadastrados! Carregando o arquivo!")
        val file_reader = FileReader(file)
        val funcionariosCadastrados = file_reader.readLines()
        file_reader.close()
        for(i in 1 until funcionariosCadastrados.size) {
            val linha = funcionariosCadastrados[i].split(",")
            funcionarios.add(Funcionario(linha[0], linha[1], linha[2].toDouble()))
        }
        if(funcionarios.size == 0) {
            println("\nO arquivo está vazio, logo não foram carregados funcionários!")
        }
        else {
            println("\nFuncionários cadastrados carregados com sucesso!")
        }
        return 1
    }
    println("\nNão há um arquivo de funcionários cadastrados! Criando um novo!")
    val file_writer = FileWriter(file)
    file_writer.write("Nome, Cargo, Salário\n")
    file_writer.close()
    return 0
}

fun atualizar_file(file: File) {
    val arquivo = mutableListOf<String>()
    arquivo.add("Nome, Cargo, Salário")
    for(f in funcionarios) {
        arquivo.add("${f.nome}, ${f.cargo}, ${f.salario}")
    }
    val file_writer = FileWriter(file)
    file_writer.write(arquivo.joinToString("\n"))
    file_writer.close()
    println("\nArquivo atualizado com sucesso!")
}

fun cadastrar_funcionario(file: File) {
    println("\nCadastro de funcionário:")
    print("Nome: ")
    val nome = readln()
    print("Cargo: ")
    val cargo = readln()
    print("Salário: ")
    val salario = readln().toDouble()

    funcionarios.add(Funcionario(nome, cargo, salario))
    atualizar_file(file)
}

fun listar_funcionarios() {
    if(funcionarios.size == 0) {
        println("\nNão há funcionários cadastrados!")
    }
    for(f in funcionarios) {
        f.informacoes()
    }
}

fun deletar_funcionario(file: File) {
    if(funcionarios.size == 0) {
        println("\nNão há funcionários cadastrados!")
    }
    else {
        print("Digite o nome do funcionário que deseja deletar: ")
        val busca = readln()
        var encontrado = false
        println("\nBuscando por funcionários chamados $busca.\n")
        for(f in funcionarios) {
            if(f.nome == busca) {
                var opc: Int
                encontrado = true
                print("Você deseja deletar:")
                f.informacoes()
                do {
                    print("1 - Sim, 2 - Não\n-> ")
                    opc = readln().toInt()
                }while(opc != 1 && opc != 2)
                if(opc == 1) {
                    funcionarios.remove(f)
                    println("\nFuncionário removido com sucesso!")
                    atualizar_file(file)
                    break
                }
            }
        }
        if(!encontrado) {
            println("\nNão foi encontrado um funcionário com esse nome!")
        }
    }
}

fun main() {
    val file = File("funcionarios.csv")
    carregar_file(file)

    println("\nSistema de Funcionários da scanDROIDERS: desenvolvido por Pedro Betti")
    println("------Seja bem vindo------")

    var opc: Int
    do {
        println("\nO que deseja realizar:")
        println("1 - Cadastrar Funcionário")
        println("2 - Listar Funcionários")
        println("3 - Deletar Funcionário")
        println("4 - Sair do Sistema")
        print("-> ")
        opc = readln().toInt()

        when(opc) {
            1 -> cadastrar_funcionario(file)
            2 -> listar_funcionarios()
            3 -> deletar_funcionario(file)
            4 -> println("\nSaindo!")
            else -> println("\nA opção digitada é inválida!")
        }
    }while(opc != 4)
}
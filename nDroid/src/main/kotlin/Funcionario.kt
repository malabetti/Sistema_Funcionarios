class Funcionario(var nome: String, var cargo: String, var salario: Double) {
    fun informacoes() {
        println("\n------------------")
        println("Nome: $nome")
        println("Cargo: $cargo")
        println("Salário: $salario")
        println("------------------")
    }
}
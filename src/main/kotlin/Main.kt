import kotlin.math.max
import kotlin.system.exitProcess

//n = 6; 4 + n % 12 + 1 = 4 + 6 + 1 = 11

/*
    Бинарное дерево поиска
Реализуйте бинарное дерево поиска для целых чисел. Программа получает на вход
последовательность целых чисел и строит из них дерево. Элементы в деревья
добавляются в соответствии с результатом поиска их места. Если элемент уже существует
в дереве, добавлять его не надо. Балансировка дерева не производится.
На вход программа получает последовательность натуральных чисел. Последовательность
завершается числом 0, которое означает конец ввода, и добавлять его в дерево не надо.
Выведите второй по величине элемент в построенном дереве.
 */

class Node(var data: Int, private var leftChild: Node ?= null, private var rightChild: Node ?= null) {

    constructor (array: IntArray) : this(array[0]){
        for (i in 1 until array.size - 1) {
            this.insert(array[i])
        }
    }

    private fun insert(value: Int) {
        if (value > data && rightChild != null) {
            rightChild!!.insert(value)
        } else if (value > data) {
            rightChild = Node(value)
        } else if (value < data && leftChild != null) {
            leftChild!!.insert(value)
        } else if (value < data) {
            leftChild = Node(value)
        }
    }

    fun preOrder () {
        print(" $data")
        leftChild?.preOrder()
        rightChild?.preOrder()
    }

    fun postOrder () {
        print(" $data")
        rightChild?.postOrder()
        leftChild?.postOrder()
    }

 /*   fun findMax(node: Node): Node{
        if (this.rightChild != null) {
            val rightChild = this.rightChild
            return findMax(rightChild)
        }
        return node
    }*/

    fun findMax(node: Node): Node{
        var currentNode = node
        while(currentNode.rightChild != null){
            currentNode = node.rightChild!!
        }
        return currentNode
    }
 /*   fun findSecondMax(): Int {
        var maxValueNode: Node = this
        var previousMaxValue: Node = this
        while(maxValueNode.rightChild != null) {
            previousMaxValue = maxValueNode
            maxValueNode = maxValueNode.rightChild!!
// (1, 3, 5, 2, 8, 7, 7, 0) не работает
        }
        return previousMaxValue.data
    }*/

    fun findSecondMax(node: Node): Node{
        if (node.rightChild == null && node.leftChild != null) {
            val leftChild: Node = node.leftChild!!
            return findMax(leftChild)
        }

        if(node.rightChild != null && node.rightChild?.leftChild == null && node.rightChild?.rightChild == null) {
            return node
        }

        return findSecondMax(node.rightChild!!)
    }
}

fun checkedInputString(input: String?){
    if (input.isNullOrBlank()){
        throw Exception("Invalid input")
    }
    println("Input is valid")
}

fun main() {
    val input = readLine()
    try {
        checkedInputString(input)
        val array = input!!.split(' ').map { it.toInt() }.toIntArray()
        val tree = Node(array)
        tree.preOrder()
        println()
        tree.postOrder()
        println()
        println("SecondMax = ${(tree.findSecondMax(tree)).data}")
    } catch (e: Exception) {
        println(e.message)
    } finally {
        println("Program has been finished")
    }
}
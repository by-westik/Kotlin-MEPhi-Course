import kotlin.math.max

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

class Node(private var data: Int, private var leftChild: Node ?= null, private var rightChild: Node ?= null) {

    constructor (array: Array<Int>) : this(array[0]){
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

    fun findSecondMax(): Int {
        var maxValueNode: Node = this
        var previousMaxValue: Node = this
        while(maxValueNode.rightChild != null) {
            previousMaxValue = maxValueNode
            maxValueNode = maxValueNode.rightChild!!

        }
        return previousMaxValue.data
    }
}

fun main() {
    val arrayTree = arrayOf(3, 4, 5, 3, 1, 0)
    val tree2 = Node(arrayTree)
    tree2.preOrder()
    println()
    tree2.postOrder()
    println()
    println(tree2.findSecondMax())
}
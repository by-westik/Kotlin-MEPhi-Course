//n = 6 n % 4 + 1 = 2 + 1 = 3
/*
3. Сортировка слиянием
Сортировка слиянием также построена на принципе "разделяй-и-властвуй", однако
реализует его несколько по-другому, нежели quickSort. А именно, вместо разделения по
опорному элементу массив просто делится пополам.

Массив разделён до последовательностей длины один

Слияние до упорядоченных пар

Слияние пар в упорядоченные четвёрки

Слияние четвёрок в общий массив

Рекурсивный алгоритм обходит получившееся дерево слияния в прямом порядке. Каждый
уровень представляет собой проход сортировки слияния - операцию, полностью
переписывающую массив.
 */

fun merge(leftArray: IntArray, rightArray: IntArray):IntArray{
    var i: Int = 0
    var j: Int = 0
    var k: Int = 0

    val result: IntArray = IntArray(leftArray.size + rightArray.size)

    while(i < leftArray.size && j < rightArray.size){
        if(leftArray[i] < rightArray[j]){
            result[k] = leftArray[i]
            k++
            i++
        } else {
            result[k] = rightArray[j]
            k++
            j++
        }
    }

    while(i < leftArray.size){
        result[k] = leftArray[i]
        i++
        k++
    }

    while(j < rightArray.size){
        result[k] = rightArray[j]
        j++
        k++
    }

    return result;
}

fun mergeSort(array: IntArray): IntArray{
    if(array.size <= 1) {
        return array;
    }

    val middle: Int = array.size / 2
    var leftArray: IntArray = array.copyOfRange(0, middle)
    var rightArray: IntArray = array.copyOfRange(middle, array.size)

 /*   leftArray.forEach { print(it) }
    print(" ")
    rightArray.forEach { print(it) }
    println()
*/
    leftArray = mergeSort(leftArray)
    rightArray = mergeSort(rightArray)

    return merge(leftArray, rightArray)
}
fun checkedInputString(input: String?){
    if (input.isNullOrBlank()){
        throw Exception("Invalid input")
    }
    println("Input is valid")
}

fun main(args: Array<String>) {
    val input = readLine()
    try {
        checkedInputString(input)
        val array = input!!.split(' ').map { it.toInt() }.toIntArray()
        val mergedArray = mergeSort(array)
        mergedArray.forEach { print(" $it") }
        println()
    } catch (e: Exception) {
        println(e.message)
    } finally {
        println("Program has been finished")
    }
}
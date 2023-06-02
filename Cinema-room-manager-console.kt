var currentIncome = 0

fun main() {
    // Ask User about Rows and Seats in Cinema
    println("Enter the number of rows:")
    val numRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val numSeatInRow = readln().toInt()
    val ourSeats = MutableList(numRows) { MutableList(numSeatInRow) { "S" } }
    while (true) {
        showTheMenu()
        var option = readln().toInt()
        when (option) {
            // Show the Cinema
            1 -> showTheSeats(numRows, numSeatInRow, ourSeats)
            2 -> buyATicket(numRows, numSeatInRow, ourSeats)
            3 -> showStatistic(numRows, numSeatInRow, ourSeats)
            0 -> break
            else -> println("Incorrect symbol")
        }
    }
}

fun showTheMenu() {
    println("\n1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit\n")
}

fun showStatistic(numRows: Int, numSeatsInRow: Int, countSeats: MutableList<MutableList<String>>) {
    var countTickets = 0
    val startCinema = numRows / 2
    var percentage = 0.0
    var totalIncome: Int = ((startCinema) * numSeatsInRow * 10) + ((numRows - startCinema) * numSeatsInRow * 8)
    for ( row in countSeats) {
        for (seat in row) {
            if (seat == "B") {
                countTickets++
            }
        }
    }

    percentage = (countTickets.toDouble() * 100) / (numRows * numSeatsInRow)
    println("\nNumber of purchased tickets: $countTickets")
    println("Percentage: ${String.format("%.2f", percentage)}%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")

}

fun showTheSeats(rows: Int, seats: Int, cinema: MutableList<MutableList<String>> ) {
    // Show the Cinema
    val ourSeats = cinema
    println("Cinema:")
    for (i in 1..seats) print(" $i")
    println()
    for ( i in ourSeats.indices) {
        println("${i + 1} ${ourSeats[i].joinToString(" ")}")
    }
}

fun buyATicket(numRows: Int, numSeatInRow: Int, ourSeats: MutableList<MutableList<String>>) {
    var priceForSeat: Int = 0
    while (priceForSeat == 0) {
        // Ask User about seat
        println("Enter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val seat = readln().toInt()


        try {
            if (ourSeats[row - 1][seat - 1].contains("B")) {
                println("\nThat ticket has already been purchased!")
            } else {
                ourSeats[row - 1][seat - 1] = "B"
                val firstHalfRows = numRows / 2

                if (numRows * numSeatInRow <= 60) {
                    priceForSeat = 10
                    currentIncome += 10
                } else if (row < firstHalfRows + 1) {
                    priceForSeat = 10
                    currentIncome += 10
                } else {
                    priceForSeat = 8
                    currentIncome += 8
                }
                println("Ticket price: $$priceForSeat")

            }
        } catch (e: Exception) {
            print("\nWrong input!\n")
        }

    }
}
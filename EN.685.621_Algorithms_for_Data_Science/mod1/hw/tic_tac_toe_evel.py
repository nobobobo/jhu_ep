class Board: 
    def __init__(self):
        # board state with 3x3 2d list, where 'O', ' ', 'X' each indicates O, empty, X
        self.state = None

    def eval_line(self, row1, col1, row2, col2, row3, col3):

        # concat characters 
        line = self.state[row1][col1] + self.state[row2][col2] + self.state[row3][col3]

        # evaluate the line combination with following evaluation. 
        # X2 = 3 points
        # X1 = 1 point
        # O2 = -3 points
        # O1 = 1 point
        if line == 'XX ' or line == 'X X' or line == ' XX': return 3
        if line == 'X  ' or line == ' X ' or line == '  X': return 1
        if line == 'OO ' or line == 'O O' or line == ' OO': return -3
        if line == 'O  ' or line == ' O ' or line == '  O': return -1

        # If 3 or -3 is got for every line (8 in total), total is 24 or -24, so set the max/min integer as 100, -100. 
        # We can evaluate if the score > 50 or score < -50 to check is the game over or not.
        if line == 'XXX': return 100
        if line == 'OOO': return -100

        return 0

    def eval(self):
        score = 0

        # scores for vertical lines
        for i in range(3):
            score = score + self.eval_line(i, 0, i, 1, i, 2)

        # scores for horizontal lines
        for i in range(3):
            score = score + self.eval_line(0, i, 1, i, 2, i)

        # scores for diagonal lines
        score = score + self.eval_line(0, 0, 1, 1, 2, 2)
        score = score + self.eval_line(0, 2, 1, 1, 2, 0)

        return score 

if __name__ == '__main__':

    # pick the first board state as sample
    state = [
            [['O', 'X', ' '],
             [' ', 'O', ' '], 
             [' ', ' ', ' ']]
             ,
            [['O', ' ', 'X'],
             [' ', ' ', ' '], 
             ['O', ' ', ' ']]
             ,
            [['O', ' ', 'X'],
             [' ', ' ', ' '], 
             [' ', ' ', ' ']]
             ,
            [['X', ' ', ' '],
             [' ', 'O', 'O'], 
             [' ', ' ', ' ']]
             ,
            [[' ', ' ', ' '],
             ['X', 'O', ' '], 
             ['O', ' ', ' ']]
             , 
            [[' ', 'X', 'X'],
             [' ', 'O', 'X'], 
             ['O', ' ', 'O']]
             , 
            [[' ', ' ', ' '],
             [' ', 'O', ' '], 
             [' ', ' ', ' ']]
             ]

    board = Board()
    for i in range(7):
   
        board.state = state[i]
        print(board.eval())
    # -6 for the first board state
    
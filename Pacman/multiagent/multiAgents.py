# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent
from pacman import GameState

class ReflexAgent(Agent):
    """
    A reflex agent chooses an action at each choice point by examining
    its alternatives via a state evaluation function.

    The code below is provided as a guide.  You are welcome to change
    it in any way you see fit, so long as you don't touch our method
    headers.
    """


    def getAction(self, gameState: GameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {NORTH, SOUTH, WEST, EAST, STOP}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState: GameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        # returneaza starea succesoare a jocului dupa ce un agent efectueaza o actiune
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        # pozitia fantomelor in starea succesoare
        newGhostStates = successorGameState.getGhostStates()
        # pozitia Pacmanului in starea succesoare
        newPos = successorGameState.getPacmanPosition()
        # pozitiile mancarii in starea succesoare
        newFood = successorGameState.getFood()
        # lista cu nr care reprezinta starea "speriata" a fantomelor din starea succesoare
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        finalValue = 0

        # Daca starea succesoare este o stare de Win => se retun un scor ft mare
        if successorGameState.isWin():
            return 999999

        # lista cu pozitia unde exista mancare in starea succesoare
        foodPos = newFood.asList()
        # lista distantelor dintre Pacman in starea succesoare si pana la fiecare punct de mancare disponibil
        distToFood = []
        # insumam starea fantomelor speriate din starea succesoare
        ghostScaredTimes = sum(newScaredTimes)

        # calculam distanta Manhattan a Pacmanului din starea succesoare catre  mancarea disponibila  si daca este diferita de 0 o adaugam in lista
        for fPos in foodPos:
            dist = manhattanDistance(fPos, newPos)
            # daca distanta e diferita de 0 o adaugam in lista distantelor
            if dist != 0:
                distToFood.append(dist)

        # daca lungimea listei este 0 atunci distanta minima calculata este si ea 0,daca nu calculam nminimul din lista pentru a stii pozitia celui mai aporpiat food-dot
        if len(distToFood) == 0:
            minDistToFood = 0
        else:
            minDistToFood = min(distToFood)

        # Distanta Manhattan catre fiecare fantoma din joc in starea curenta
        # pozitia fantomelor in starea curenta
        posToGhostCurrState = []
        for ghost in currentGameState.getGhostStates():
            posToGhostCurrState.append(ghost.getPosition())
        # distanta Manhattan de la pozitia Pacmanul din starea succesoare catre pozitia fantomelor din starea curenta
        distanceCurrPosToGhost = []
        for pos in posToGhostCurrState:
            distanceCurrPosToGhost.append(manhattanDistance(newPos, pos))

        # Distanta Manhattan catre fiecare fantoma din joc din starea succesoare
        # pozitia fantomelor in starea succesoare
        positionsOfGhosts = []
        for ghost in newGhostStates:
            positionsOfGhosts.append(ghost.getPosition())
        # distanta Manhattan de la pozitia Pacmanul din starea succesoare catre pozitia fantomelor din starea succesoare
        distanceToGhost = []
        for pos in positionsOfGhosts:
            distanceToGhost.append(manhattanDistance(newPos, pos))

        # pozitia curenta a fantomei
        currGhostDist = manhattanDistance(newPos, currentGameState.getGhostPosition(1))

        # Daca fantomele sunt speriate alege distanta mai mica pt ca e mai buna
        if ghostScaredTimes > 0:
            if min(distanceCurrPosToGhost) < min(distanceToGhost):
                finalValue = currGhostDist + successorGameState.getScore()
                finalValue += 100
            else:
                finalValue -= 5
        # Daca fantomele nu sunt speriate, distranta mai mare de fantome e recomandata
        else:
            if min(distanceCurrPosToGhost) < min(distanceToGhost):
                finalValue -= 5
            else:
                finalValue = currGhostDist + successorGameState.getScore()
                finalValue += 100

        # calculam cantitatea de mancare ramasa
        foodLeft = len(foodPos)

        # reducem scorul total cu distanta minima pe care am aflat-o pana la cea mai apropiata mancare
        finalValue -= minDistToFood

        # aici marim scorul daca pozitia actuala este fix pe pozitia mancarii
        if newPos in currentGameState.getCapsules():
            finalValue += 100

        # marim scorul daca ramane doar un singur punct in care gasim mancare
        if foodLeft == 1:
            finalValue += 1000

        # marim scorul daca sunt mai putine mancaruri disponibile in starea succesoare
        if foodLeft < len(currentGameState.getFood().asList()):
            finalValue += 100

        # decrementam scorul daca Pacman se opreste
        if action == Directions.STOP:
            # penalitate pt stop
            finalValue -= 10

        # returnam scorul final
        return finalValue

def scoreEvaluationFunction(currentGameState: GameState):
    """
    This default evaluation function just returns the score of the state.
    The score is the same one displayed in the Pacman GUI.

    This evaluation function is meant for use with adversarial search agents
    (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
    This class provides some common elements to all of your
    multi-agent searchers.  Any methods defined here will be available
    to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

    You *do not* need to make any changes here, but you can if you want to
    add functionality to all your adversarial search agents.  Please do not
    remove anything, however.

    Note: this is an abstract class: one that should not be instantiated.  It's
    only partially specified, and designed to be extended.  Agent (game.py)
    is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
    Your minimax agent (question 2)
    """

    def getAction(self, gameState: GameState):
        """
        Returns the minimax action from the current gameState using self.depth
        and self.evaluationFunction.

        Here are some method calls that might be useful when implementing minimax.

        gameState.getLegalActions(agentIndex):
        Returns a list of legal actions for an agent
        agentIndex=0 means Pacman, ghosts are >= 1

        gameState.generateSuccessor(agentIndex, action):
        Returns the successor game state after an agent takes an action

        gameState.getNumAgents():
        Returns the total number of agents in the game

        gameState.isWin():
        Returns whether or not the game state is a winning state

        gameState.isLose():
        Returns whether or not the game state is a losing state
        """
        "*** YOUR CODE HERE ***"
        numberOfGhosts = gameState.getNumAgents() - 1

        # Folosit doar pt agentul Pacman deoarece agentindex este mereu 0
        def maxLevel(gameState, depth):
            currDepth = depth + 1
            if gameState.isWin() or gameState.isLose() or currDepth == self.depth:  # Terminal Test
                return self.evaluationFunction(gameState)
            maxvalue = -999999
            actions = gameState.getLegalActions(0)
            for action in actions:
                successor = gameState.generateSuccessor(0, action)
                maxvalue = max(maxvalue, minLevel(successor, currDepth, 1))
            return maxvalue

        # Pentru toate fantomele
        def minLevel(gameState, depth, agentIndex):
            minvalue = 999999
            if gameState.isWin() or gameState.isLose():  # Terminal Test
                return self.evaluationFunction(gameState)
            actions = gameState.getLegalActions(agentIndex)
            for action in actions:
                successor = gameState.generateSuccessor(agentIndex, action)
                if agentIndex == (gameState.getNumAgents() - 1):
                    minvalue = min(minvalue, maxLevel(successor, depth))
                else:
                    minvalue = min(minvalue, minLevel(successor, depth, agentIndex + 1))
            return minvalue

        # Root level action
        actions = gameState.getLegalActions(0)
        currentScore = -999999
        returnAction = ''
        for action in actions:
            nextState = gameState.generateSuccessor(0, action)
            # Urmatorul nivel este un nivel MIN => aplicam MIN pt succesorii radacinii
            score = minLevel(nextState, 0, 1)
            # Alege actiunea alegand Maximum dintre succesori
            if score > currentScore:
                returnAction = action
                currentScore = score

        return returnAction

class AlphaBetaAgent(MultiAgentSearchAgent):
    """
    Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState: GameState):
        """
        Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        # Used only for pacman agent hence agentindex is always 0.
        def maxLevel(gameState, depth, alpha, beta):
            currDepth = depth + 1
            if gameState.isWin() or gameState.isLose() or currDepth == self.depth:  # Terminal Test
                return self.evaluationFunction(gameState)
            maxvalue = -999999
            actions = gameState.getLegalActions(0)
            alpha1 = alpha
            for action in actions:
                successor = gameState.generateSuccessor(0, action)
                maxvalue = max(maxvalue, minLevel(successor, currDepth, 1, alpha1, beta))
                if maxvalue > beta:
                    return maxvalue
                alpha1 = max(alpha1, maxvalue)
            return maxvalue

        # For all ghosts.
        def minLevel(gameState, depth, agentIndex, alpha, beta):
            minvalue = 999999
            if gameState.isWin() or gameState.isLose():  # Terminal Test
                return self.evaluationFunction(gameState)
            actions = gameState.getLegalActions(agentIndex)
            beta1 = beta
            for action in actions:
                successor = gameState.generateSuccessor(agentIndex, action)
                if agentIndex == (gameState.getNumAgents() - 1):
                    minvalue = min(minvalue, maxLevel(successor, depth, alpha, beta1))
                    if minvalue < alpha:
                        return minvalue
                    beta1 = min(beta1, minvalue)
                else:
                    minvalue = min(minvalue, minLevel(successor, depth, agentIndex + 1, alpha, beta1))
                    if minvalue < alpha:
                        return minvalue
                    beta1 = min(beta1, minvalue)
            return minvalue

        # Alpha-Beta Pruning
        actions = gameState.getLegalActions(0)
        currentScore = -999999
        returnAction = ''
        alpha = -999999
        beta = 999999
        for action in actions:
            nextState = gameState.generateSuccessor(0, action)
            # Next level is a min level. Hence calling min for successors of the root.
            score = minLevel(nextState, 0, 1, alpha, beta)
            # Choosing the action which is Maximum of the successors.
            if score > currentScore:
                returnAction = action
                currentScore = score
            # Updating alpha value at root.
            if score > beta:
                return returnAction
            alpha = max(alpha, score)
        return returnAction


""""
Pentru a apela in terminal:
    - cu o singura fantoma:
        python2 pacman.py --frameTime 0.1 -p ExpectimaxAgent -k 1 -l minimaxClassic -a depth=4
    - cu doua fantome:
        python2 pacman.py --frameTime 0.1 -p ExpectimaxAgent -k 2 -l minimaxClassic -a depth=4
    python2 pacman.py --frameTime 0.1 -p ExpectimaxAgent -k 2 -l testClassic -a depth=4
    python2 pacman.py --frameTime 0.1 -p ExpectimaxAgent -k 2 -l trappedClassic -a depth=4
"""

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
        Your expectimax agent (question 4)
    """

    def getAction(self, gameState: GameState):
        """
        Returns the expectimax action using self.depth and self.evaluationFunction

        All ghosts should be modeled as choosing uniformly at random from their
        legal moves.
        """
        "*** YOUR CODE HERE ***"
        util.raiseNotDefined()

def betterEvaluationFunction(currentGameState: GameState):
    """
    Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
    evaluation function (question 5).

    DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

# Abbreviation
better = betterEvaluationFunction

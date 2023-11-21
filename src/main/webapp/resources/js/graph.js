let graph_click_enabled = false;

const elt = document.getElementById('calculator');
const calculator = Desmos.GraphingCalculator(elt, {
    keypad: false,
    expressions: false,
    settingsMenu: false,
    zoomButtons: false,
    invertedColors: true,
    xAxisLabel: 'x',
    yAxisLabel: 'y',
    xAxisStep: 1,
    yAxisStep: 1,
});

calculator.setMathBounds({
    left: -4,
    right: 4,
    bottom: -4,
    top: 4
});

let newDefaultState = calculator.getState();
calculator.setDefaultState(newDefaultState);

function drawFig(R){
    calculator.setExpression({ id: 'triangle', latex: `\\polygon((0, ${R}), (${R}, 0), (0, 0))`, color: Desmos.Colors.RED, opacity: 0.3});
    calculator.setExpression({ id: 'rectangle', latex: `\\polygon((${-R}, 0), (${-R}, ${R/2}), (0, ${R/2}), (0, 0))`, color: Desmos.Colors.RED, opacity: 0.3});
    //calculator.setExpression({id: 'circle', latex: `r<=${R/2}`, color: Desmos.Colors.RED});
    calculator.setExpression({id: 'circle2', latex: `x^{2}+y^{2}\\ <=\\left(\\frac{${R}}{2}\\right)^{2}\\ \\left\\{y\\ <0\\right\\}\\left\\{x>0\\right\\}`, color: Desmos.Colors.RED});
}

function drawPointXY(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.RED
    });
}
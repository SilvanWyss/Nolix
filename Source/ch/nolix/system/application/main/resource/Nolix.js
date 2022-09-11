define("Core/CommonType/CommonTypeHelper/GlobalStringHelper", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class GlobalStringHelper {
        static createStringOfSpaces(spaceCount) {
            if (spaceCount < 0) {
                throw new Error('The given space count is negative.');
            }
            var string = '';
            for (var i = 1; i <= spaceCount; i++) {
                string += ' ';
            }
            return string;
        }
        static createStringWithReplacedParts(originalString, searchedString, replacingString) {
            var string = originalString;
            while (string.includes(searchedString)) {
                string = string.replace(searchedString, replacingString);
            }
            return string;
        }
    }
    exports.GlobalStringHelper = GlobalStringHelper;
});
define("Core/Constant/FontCodeCatalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FontCodeCatalogue {
        constructor() { }
    }
    FontCodeCatalogue.ARIAL = 'Arial';
    FontCodeCatalogue.ARIAL_BLACK = 'Arial Black';
    FontCodeCatalogue.COMIC_SANS_MS = 'Comic Sans MS';
    FontCodeCatalogue.IMPACT = 'Impact';
    FontCodeCatalogue.LUCIDA_CONSOLE = 'Lucida Console';
    FontCodeCatalogue.PAPYRUS = 'Papyrus';
    FontCodeCatalogue.TAHOMA = 'Tahoma';
    FontCodeCatalogue.VERDANA = 'Verdana';
    exports.FontCodeCatalogue = FontCodeCatalogue;
});
define("Core/Constant/PascalCaseNameCatalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class PascalCaseNameCatalogue {
        constructor() { }
    }
    PascalCaseNameCatalogue.CURSOR_POSITION = 'CursorPosition';
    PascalCaseNameCatalogue.HEIGHT = 'Height';
    PascalCaseNameCatalogue.SIZE = 'Size';
    PascalCaseNameCatalogue.WIDTH = 'Width';
    exports.PascalCaseNameCatalogue = PascalCaseNameCatalogue;
});
define("Core/Constant/StringCatalogue", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class StringCatalogue {
        constructor() { }
    }
    StringCatalogue.BINARY_PREFIX = '0b';
    StringCatalogue.EMPTY = '';
    StringCatalogue.HEXADECIMAL_PREFIX = '0x';
    exports.StringCatalogue = StringCatalogue;
});
define("Core/Container/Container", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Container {
        contains(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return true;
                }
            }
            return false;
        }
        containsAny() {
            for (const e of this) {
                return true;
            }
            return false;
        }
        containsGivenElement(element) {
            for (const e of this) {
                if (Object.is(e, element)) {
                    return true;
                }
            }
            return false;
        }
        containsOne() {
            return (this.getSize() === 1);
        }
        forEach(elementTaker) {
            for (const e of this) {
                elementTaker(e);
            }
        }
        getOneAsString() {
            return this.getRefOne().toString();
        }
        getRefOne() {
            var element = undefined;
            for (const e of this) {
                if (element !== undefined) {
                    throw new Error('The current List contains none or several elements.');
                }
                element = e;
            }
            if (element === undefined) {
                throw new Error('The current List contains none or several elements.');
            }
            return element;
        }
        isEmpty() {
            return (this.getSize() === 0);
        }
        toString() {
            return this.toStringWithSeparator(',');
        }
        toStringInBrackets() {
            return ('(' + this.toString() + ')');
        }
        toStringInBracketsWithSeparator(separator) {
            return ('(' + this.toStringWithSeparator(separator) + ')');
        }
        toStringWithSeparator(separator) {
            if (separator === null) {
                throw new Error('The given separator is null.');
            }
            if (separator === undefined) {
                throw new Error('The given separator is undefined.');
            }
            var string = '';
            var begin = true;
            for (const e of this) {
                if (begin) {
                    string += e.toString();
                    begin = false;
                }
                else {
                    string += separator + e.toString();
                }
            }
            return string;
        }
    }
    exports.Container = Container;
});
define("Core/Container/LinkedListNode", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedListNode {
        constructor(element) {
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.element = element;
        }
        contains(element) {
            return (Object.is(this.element, element));
        }
        getRefElement() {
            return this.element;
        }
        getRefNextNode() {
            if (this.nextNode === undefined) {
                throw new Error('The current list node does not have a next node.');
            }
            return this.nextNode;
        }
        hasNextNode() {
            return (this.nextNode !== undefined);
        }
        hasNextNodeWith(element) {
            return (this.nextNode !== undefined && this.nextNode.element === element);
        }
        removeNextNode() {
            this.nextNode = undefined;
        }
        setNextNode(nextNode) {
            if (nextNode === null) {
                throw new Error('The given next node is null.');
            }
            if (nextNode === undefined) {
                throw new Error('The given next node is undefined.');
            }
            this.nextNode = nextNode;
        }
    }
    exports.LinkedListNode = LinkedListNode;
});
define("Core/Container/LinkedListIterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedListIterator {
        constructor(currentNode) {
            this.currentNode = undefined;
            this.lastElement = undefined;
            this.currentNode = currentNode;
        }
        next() {
            if (this.currentNode === undefined) {
                return { done: true, value: undefined };
            }
            this.lastElement = this.currentNode.getRefElement();
            if (!this.currentNode.hasNextNode()) {
                this.currentNode = undefined;
                return { done: false, value: this.lastElement };
            }
            this.currentNode = this.currentNode.getRefNextNode();
            return { done: false, value: this.lastElement };
        }
        return() {
            return { done: true, value: this.lastElement };
        }
    }
    exports.LinkedListIterator = LinkedListIterator;
});
define("Core/Container/LinkedList", ["require", "exports", "Core/Container/Container", "Core/Container/LinkedListIterator", "Core/Container/LinkedListNode"], function (require, exports, Container_1, LinkedListIterator_1, LinkedListNode_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedList extends Container_1.Container {
        constructor() {
            super(...arguments);
            this.elementCount = 0;
            this.beginNode = undefined;
            this.endNode = undefined;
        }
        static withElement(element) {
            const list = new LinkedList();
            list.addAtEnd(element);
            return list;
        }
        addAtBegin(element) {
            const newNode = new LinkedListNode_1.LinkedListNode(element);
            if (this.elementCount === 0) {
                this.endNode = newNode;
            }
            else {
                newNode.setNextNode(this.beginNode);
            }
            this.beginNode = newNode;
            this.elementCount++;
        }
        addAtEnd(element) {
            const newNode = new LinkedListNode_1.LinkedListNode(element);
            if (this.elementCount === 0) {
                this.beginNode = newNode;
            }
            else {
                this.endNode.setNextNode(newNode);
            }
            this.endNode = newNode;
            this.elementCount++;
        }
        addElementsAtEnd(elements) {
            for (const e of elements) {
                this.addAtEnd(e);
            }
        }
        clear() {
            if (this.elementCount > 0) {
                this.beginNode = undefined;
                this.endNode = undefined;
                this.elementCount = 0;
            }
        }
        getRefAt(index) {
            if (index < 1) {
                throw new Error('The given index is not positive.');
            }
            var i = 1;
            for (const e of this) {
                if (i === index) {
                    return e;
                }
                i++;
            }
            throw new Error('The given index is bigger than the size of the current List.');
        }
        getRefFirst() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.beginNode.getRefElement();
        }
        getRefFirstByCondition(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            throw new Error("The current List does not contain an element the given selector selects.");
        }
        getRefFirstByConditionOrNull(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            return null;
        }
        getRefFirstOrNull() {
            if (this.elementCount === 0) {
                return null;
            }
            return this.beginNode.getRefElement();
        }
        getRefLast() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.endNode.getRefElement();
        }
        getRefSelected(selector) {
            const selectedElements = new LinkedList();
            for (const e of this) {
                if (selector(e)) {
                    selectedElements.addAtEnd(e);
                }
            }
            return selectedElements;
        }
        getSize() {
            return this.elementCount;
        }
        refill(element) {
            this.clear();
            this.addElementsAtEnd(element);
        }
        removeFirst() {
            if (this.elementCount === 0) {
                throw new Error('The current List is empty.');
            }
            if (this.containsOne()) {
                this.clear();
            }
            else {
                this.beginNode = this.beginNode.getRefNextNode();
                this.elementCount--;
            }
        }
        removeFirst2(element) {
            if (this.elementCount === 0) {
                return;
            }
            if (element === this.getRefFirst()) {
                this.removeFirst();
                return;
            }
            var iteratorNode = this.beginNode;
            while (iteratorNode.hasNextNode()) {
                if (iteratorNode.hasNextNodeWith(element)) {
                    this.removeNextNode(iteratorNode);
                    return;
                }
                iteratorNode = iteratorNode.getRefNextNode();
            }
            throw new Error('The current List does not contain the given element.');
        }
        to(extractor) {
            const list = new LinkedList();
            for (const e of this) {
                list.addAtEnd(extractor(e));
            }
            return list;
        }
        toStrings() {
            const strings = new LinkedList();
            for (const e of this) {
                strings.addAtEnd(e.toString());
            }
            return strings;
        }
        [Symbol.iterator]() {
            return new LinkedListIterator_1.LinkedListIterator(this.beginNode);
        }
        removeNextNode(node) {
            const nextNode = node.getRefNextNode();
            if (!nextNode.hasNextNode()) {
                nextNode.removeNextNode();
                this.endNode = node;
            }
            else {
                node.setNextNode(nextNode.getRefNextNode());
            }
            this.elementCount--;
            nextNode.removeNextNode();
        }
    }
    exports.LinkedList = LinkedList;
});
define("Core/Container/SingleContainer", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class SingleContainer {
        constructor(optionalElement) {
            this.element = optionalElement;
        }
        static withElement(element) {
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            return new SingleContainer(element);
        }
        static withoutElement() {
            return new SingleContainer(undefined);
        }
        contains(element) {
            return (Object.is(this.element, element));
        }
        containsAny() {
            return (this.element !== undefined);
        }
        getRefElement() {
            if (this.element === undefined) {
                throw new Error('The current SingleContainer does not contain an element.');
            }
            return this.element;
        }
        isEmpty() {
            return (this.element === undefined);
        }
    }
    SingleContainer.EMPTY_CONTAINER = new SingleContainer(undefined);
    exports.SingleContainer = SingleContainer;
});
define("Core/Container/Pair/Pair", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Pair {
        constructor(element1, element2) {
            if (element1 === null) {
                throw new Error('The given element1 is null.');
            }
            if (element1 === undefined) {
                throw new Error('The given element1 is undefined.');
            }
            if (element2 === null) {
                throw new Error('The given element2 is null.');
            }
            if (element2 === undefined) {
                throw new Error('The given element2 is undefined.');
            }
            this.element1 = element1;
            this.element2 = element2;
        }
        getRefElement1() {
            return this.element1;
        }
        getRefElement2() {
            return this.element2;
        }
    }
    exports.Pair = Pair;
});
define("Core/Container/Caching/CachingContainer", ["require", "exports", "Core/Container/LinkedList", "Core/Container/Pair/Pair"], function (require, exports, LinkedList_1, Pair_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CachingContainer {
        constructor() {
            this.elements = new LinkedList_1.LinkedList();
        }
        contains(element) {
            return this.elements.contains(e => Object.is(e, element));
        }
        containsWithId(id) {
            return this.elements.contains(e => e.getRefElement1() === id);
        }
        getRefById(id) {
            return this.elements.getRefFirstByCondition(e => e.getRefElement1() === id).getRefElement2();
        }
        registerAtId(id, element) {
            console.log('The current CachingContainer registers an element at the given id \'' + id + '\'.');
            if (id === null) {
                throw new Error('The given id is null.');
            }
            if (id === undefined) {
                throw new Error('The given id is undefined.');
            }
            if (id.length === 0) {
                throw new Error('The given id is empty.');
            }
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.assertDoesNotContainId(id);
            this.assertDoesNotContain(element);
            this.elements.addAtEnd(new Pair_1.Pair(id, element));
        }
        assertDoesNotContain(element) {
            if (this.contains(element)) {
                throw new Error('The current CachingContainer contains already the given element.');
            }
        }
        assertDoesNotContainId(id) {
            if (this.containsWithId(id)) {
                throw new Error('The current CachingContainer contains already an element with the given id.');
            }
        }
    }
    exports.CachingContainer = CachingContainer;
});
define("Core/Container/Matrix/MatrixIterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class MatrixIterator {
        constructor(parentMatrix) {
            if (parentMatrix === undefined) {
                console.log('The given parentMatrix is undefined.');
            }
            if (parentMatrix === null) {
                console.log('The given parentMatrix is null.');
            }
            this.parentMatrix = parentMatrix;
            this.currentElementIndex = 1;
        }
        next() {
            if (this.currentElementIndex > this.parentMatrix.getSize()) {
                return { done: true, value: undefined };
            }
            this.currentElementIndex++;
            return {
                done: this.currentElementIndex > this.parentMatrix.getSize(),
                value: this.parentMatrix.getRefAt(this.currentElementIndex - 1)
            };
        }
    }
    exports.MatrixIterator = MatrixIterator;
});
define("Core/Container/Matrix/Matrix", ["require", "exports", "Core/Container/Container", "Core/Container/Matrix/MatrixIterator"], function (require, exports, Container_2, MatrixIterator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Matrix extends Container_2.Container {
        constructor() {
            super();
            this.elements = new Array();
        }
        addColumn(elements) {
            if (this.isEmpty()) {
                this.addColumnWhenIsEmpty(elements);
            }
            else {
                this.addColumnWhenIsNotEmpty(elements);
            }
        }
        addRow(elements) {
            if (this.isEmpty()) {
                this.addRowWhenIsEmpty(elements);
            }
            else {
                this.addRowWhenIsNotEmpty(elements);
            }
        }
        getColumnCount() {
            const rowCount = this.getRowCount();
            if (rowCount === 0) {
                return 0;
            }
            return this.elements[0].length;
        }
        getColumnIndexOf(index) {
            this.assertContainsAt(index);
            return ((index - 1) % this.getColumnCount() + 1);
        }
        getCopy() {
            const matrix = new Matrix();
            const rowCount = this.getRowCount();
            for (var i = 0; i < rowCount; i++) {
                matrix.addRow(this.elements[i]);
            }
            return matrix;
        }
        getRefAt(index) {
            return this.getRefAtRowAndColumn(this.getRowIndexOf(index), this.getColumnIndexOf(index));
        }
        getRefAtRowAndColumn(rowIndex, columnIndex) {
            this.assertContainsAtRowAndColumn(rowIndex, columnIndex);
            return this.elements[rowIndex - 1][columnIndex - 1];
        }
        getRowCount() {
            return this.elements.length;
        }
        getRowIndexOf(index) {
            this.assertContainsAt(index);
            return (Math.floor((index - 1) / this.getColumnCount()) + 1);
        }
        getSize() {
            if (this.elements.length === 0) {
                return 0;
            }
            return (this.elements.length * this.elements[0].length);
        }
        setAtIndex(index, element) {
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.elements[this.getRowIndexOf(index) - 1][this.getColumnIndexOf(index) - 1] = element;
        }
        setAtRowAndColumn(rowIndex, columnIndex, element) {
            this.assertContainsAtRowAndColumn(rowIndex, columnIndex);
            if (element === null) {
                throw new Error('The given element is null.');
            }
            if (element === undefined) {
                throw new Error('The given element is undefined.');
            }
            this.elements[rowIndex - 1][columnIndex - 1] = element;
        }
        [Symbol.iterator]() {
            return new MatrixIterator_1.MatrixIterator(this);
        }
        addColumnWhenIsEmpty(elements) {
            if (elements.length == 0) {
                throw new Error('There are not given any elements.');
            }
            const column = new Array(elements.length);
            for (const e of elements) {
                const array = new Array(1);
                array[0] = e;
                this.elements.push(array);
            }
        }
        addColumnWhenIsNotEmpty(elements) {
            if (elements.length != this.getRowCount()) {
                throw new Error('There are given ' + elements.length + ' elements.');
            }
            const rowCount = this.getRowCount();
            for (var i = 0; i < rowCount; i++) {
                this.elements[i].push(elements[i]);
            }
        }
        addRowWhenIsEmpty(elements) {
            if (elements.length == 0) {
                throw new Error('There are not given any elements.');
            }
            this.elements.push(Array.from(elements));
        }
        addRowWhenIsNotEmpty(elements) {
            if (elements.length != this.getColumnCount()) {
                throw new Error('There are given ' + elements.length + ' elements.');
            }
            this.elements.push(Array.from(elements));
        }
        assertContainsAt(index) {
            if (index < 1) {
                throw new Error('The given index ' + index + ' is not positive.');
            }
            if (index > this.getSize()) {
                throw new Error('The given index ' + index + ' is bigger than the number of elements of the current Matrix.');
            }
        }
        assertContainsAtRowAndColumn(rowIndex, columnIndex) {
            if (rowIndex < 1) {
                throw new Error('The given rowIndex ' + rowIndex + ' is not positive.');
            }
            if (rowIndex > this.getRowCount()) {
                throw new Error('The given rowIndex '
                    + rowIndex
                    + ' is bigger than the number of rows of the current Matrix, which is '
                    + this.getRowCount()
                    + ' .');
            }
            if (columnIndex < 1) {
                throw new Error('The given columnIndex ' + columnIndex + ' is not positive.');
            }
            if (columnIndex > this.getColumnCount()) {
                throw new Error('The given columnIndex '
                    + columnIndex
                    + ' is bigger than the number of columns of the current Matrix, which is'
                    + this.getColumnCount()
                    + ' .');
            }
        }
    }
    exports.Matrix = Matrix;
});
define("Core/Document/Node/Node", ["require", "exports", "Core/Container/LinkedList"], function (require, exports, LinkedList_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Node {
        constructor() {
            this.attributes = new LinkedList_2.LinkedList();
        }
        static createOriginStringFromReproducingString(reproducinString) {
            var originString = '';
            for (var i = 0; i < reproducinString.length; i++) {
                switch (reproducinString[i]) {
                    case '$':
                        switch (reproducinString[i + 1]) {
                            case 'D':
                                originString += '.';
                                break;
                            case 'M':
                                originString += '.';
                                break;
                            case 'X':
                                originString += '.';
                                break;
                            case 'O':
                                originString += '.';
                                break;
                            case 'C':
                                originString += '.';
                                break;
                            default:
                                throw new Error('The given reproducing string is not valid.');
                        }
                        i++;
                        break;
                    default:
                        originString += reproducinString[i];
                        break;
                }
            }
            return originString;
        }
        static createReproducingString(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            return string
                .replace('$', this.DOLLAR_SYMBOL_CODE)
                .replace('.', this.DOT_CODE)
                .replace(',', this.COMMA_CODE)
                .replace('(', this.OPEN_BRACKET_CODE)
                .replace(')', this.CLOSED_BRACKET_CODE);
        }
        static fromNumberPair(numberPair) {
            return new Node()
                .addAttribute(Node.withHeaderFromNumber(numberPair.getRefElement1()))
                .addAttribute(Node.withHeaderFromNumber(numberPair.getRefElement2()));
        }
        static fromString(string) {
            return new Node().resetFrom(string);
        }
        static withAttribute(attribute) {
            return new Node().addAttribute(attribute);
        }
        static withHeader(header) {
            return new Node().setHeader(header);
        }
        static withHeaderAndAttribute(header, attribute) {
            return new Node().setHeader(header).addAttribute(attribute);
        }
        static withHeaderAndAttributes(header, attributes) {
            return new Node().setHeader(header).addAttributes(attributes);
        }
        static withHeaderFromNumber(header) {
            return new Node().setHeader(header.toString());
        }
        addAttribute(attribute) {
            this.attributes.addAtEnd(attribute.getCopy());
            return this;
        }
        addAttributeFromNumber(attribute) {
            this.addAttribute(Node.withHeaderFromNumber(attribute));
            return this;
        }
        addAttributes(attributes) {
            attributes.forEach(a => this.addAttribute(a));
            return this;
        }
        containsAttributes() {
            return this.attributes.containsAny();
        }
        getAttributeCount() {
            return this.attributes.getSize();
        }
        getCopy() {
            var copy = new Node();
            if (this.header !== undefined) {
                copy.setHeader(this.getHeader());
            }
            this.attributes.forEach(a => copy.addAttribute(a));
            return copy;
        }
        getHeader() {
            if (this.header === undefined) {
                throw new Error("The current document node does not have a header.");
            }
            return this.header;
        }
        getOneAttributeAsNumber() {
            return this.getRefOneAttribute().toNumber();
        }
        getOneAttributeHeader() {
            return this.getRefOneAttribute().getHeader();
        }
        getRefAttributeAtIndex(index) {
            return this.attributes.getRefAt(index);
        }
        getRefAttributes() {
            return this.attributes;
        }
        getRefFirstAttributeWithHeader(header) {
            return this.attributes.getRefFirstByCondition(a => a.hasGivenHeader(header));
        }
        getRefOneAttribute() {
            return this.attributes.getRefOne();
        }
        getReproducingHeader() {
            return Node.createReproducingString(this.getHeader());
        }
        hasHeader() {
            return (this.header !== undefined);
        }
        hasGivenHeader(header) {
            return (this.header !== undefined && this.header === header);
        }
        removeAttributes() {
            this.attributes.clear();
            return this;
        }
        removeHeader() {
            this.header = undefined;
            return this;
        }
        reset() {
            this.removeHeader();
            this.removeAttributes();
            return this;
        }
        resetFrom(string) {
            this.reset();
            if (this.setAndGetEndIndex(string, 0) !== string.length - 1) {
                throw new Error('The given string does not represent a document node.');
            }
            return this;
        }
        setHeader(header) {
            if (header === null) {
                throw new Error("The given header is null.");
            }
            if (header === undefined) {
                throw new Error('The given header is undefined.');
            }
            if (header.length === 0) {
                throw new Error("The given header is empty.");
            }
            this.header = header;
            return this;
        }
        toNumber() {
            if (this.attributes.containsAny()) {
                throw new Error('The current Node does not represent a number: ' + this.toString());
            }
            return Number(this.header);
        }
        toString() {
            var string = '';
            if (this.header !== undefined) {
                string += this.getReproducingHeader();
            }
            if (this.attributes.containsAny()) {
                string += '(' + this.getRefAttributes().toString() + ')';
            }
            return string;
        }
        setAndGetEndIndex(string, startIndex) {
            var index = startIndex;
            var endIndex = -1;
            while (index < string.length) {
                const character = string[index];
                if (character === '(') {
                    break;
                }
                else if (character === ',' || character === ')') {
                    endIndex = index - 1;
                    break;
                }
                index++;
            }
            if (index > startIndex) {
                this.setHeader(string.substring(startIndex, index));
            }
            if (index === string.length) {
                return (index - 1);
            }
            if (endIndex !== -1) {
                return endIndex;
            }
            if (index < string.length) {
                const documentNode = new Node();
                index = documentNode.setAndGetEndIndex(string, index + 1) + 1;
                this.addAttribute(documentNode);
            }
            while (index < string.length) {
                switch (string[index]) {
                    case ',':
                        const documentNode = new Node();
                        index = documentNode.setAndGetEndIndex(string, index + 1) + 1;
                        this.addAttribute(documentNode);
                        break;
                    case ')':
                        return index;
                    default:
                        throw new Error('The given string does not represent a Node: ' + string);
                }
            }
        }
    }
    Node.DOT_CODE = '$D';
    Node.COMMA_CODE = '$M';
    Node.DOLLAR_SYMBOL_CODE = '$X';
    Node.OPEN_BRACKET_CODE = '$O';
    Node.CLOSED_BRACKET_CODE = '$C';
    exports.Node = Node;
});
define("Core/Document/ChainedNode/Task", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var Task;
    (function (Task) {
        Task[Task["DO_NOTHING"] = 0] = "DO_NOTHING";
        Task[Task["READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE"] = 1] = "READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE";
        Task[Task["READ_NEXT_NODE"] = 2] = "READ_NEXT_NODE";
    })(Task = exports.Task || (exports.Task = {}));
});
define("Core/Document/ChainedNode/ChainedNode", ["require", "exports", "Core/Container/LinkedList", "Core/Document/Node/Node", "Core/Constant/StringCatalogue", "Core/Document/ChainedNode/Task"], function (require, exports, LinkedList_3, Node_1, StringCatalogue_1, Task_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ChainedNode {
        constructor() {
            this.header = undefined;
            this.nextNode = undefined;
            this.attributes = new LinkedList_3.LinkedList();
        }
        static createReproducingString(string) {
            var reprodudingString = '';
            for (var i = 0; i < string.length; i++) {
                switch (string[i]) {
                    case '$':
                        reprodudingString += this.DOLLAR_SYMBOL_CODE;
                        break;
                        ;
                    case '.':
                        reprodudingString += this.DOT_CODE;
                        break;
                    case ',':
                        reprodudingString += this.COMMA_CODE;
                        break;
                    case '(':
                        reprodudingString += this.OPEN_BRACKET_CODE;
                        break;
                    case ')':
                        reprodudingString += this.CLOSED_BRACKET_CODE;
                        break;
                    default:
                        reprodudingString += string[i];
                        break;
                }
            }
            return reprodudingString;
        }
        static fromNode(node) {
            const chainedNode = new ChainedNode();
            if (node.hasHeader()) {
                chainedNode.setHeader(node.getHeader());
            }
            for (const a of node.getRefAttributes()) {
                chainedNode.addAttributeFromNode(a);
            }
            return chainedNode;
        }
        static fromString(string) {
            const chainedNode = new ChainedNode();
            chainedNode.resetFromString(string);
            return chainedNode;
        }
        static withHeader(header) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            return chainedNode;
        }
        static withHeaderAndAttribute(header, attribute) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.attributes.addAtEnd(attribute);
            return chainedNode;
        }
        static withHeaderAndAttributeFromNode(header, attribute) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.addAttributeFromNode(attribute);
            return chainedNode;
        }
        static withHeaderAndAttributes(header, attributes) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.setAttributes(attributes);
            return chainedNode;
        }
        static withHeaderAndAttributesFromNodes(header, attributes) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header);
            chainedNode.setAttributesFromNodes(attributes);
            return chainedNode;
        }
        static withHeaderFromNumber(header) {
            const chainedNode = new ChainedNode();
            chainedNode.setHeader(header.toString());
            return chainedNode;
        }
        containsAttributes() {
            return this.attributes.containsAny();
        }
        getAttributeCount() {
            return this.attributes.getSize();
        }
        getAttributeAt(index) {
            return this.attributes.getRefAt(index);
        }
        getAttributeAtAsNode(index) {
            return this.getAttributeAt(index).toNode();
        }
        getAttributes() {
            return this.attributes;
        }
        getAttributesAsNodes() {
            return this.attributes.to(a => a.toNode());
        }
        getAttributesAsString() {
            return this.attributes.toString();
        }
        getAttributesAsStrings() {
            return this.attributes.toStrings();
        }
        getFirstAttribute() {
            return this.attributes.getRefFirst();
        }
        getFirstAttribtueAsNode() {
            return this.getFirstAttribute().toNode();
        }
        getFirstAttributeAsString() {
            return this.getFirstAttribute().toString();
        }
        getHeader() {
            if (this.header === undefined) {
                throw new Error('The current ChainedNode does not have a header.');
            }
            return this.header;
        }
        getHeaderOrEmptyString() {
            if (this.header === undefined) {
                return StringCatalogue_1.StringCatalogue.EMPTY;
            }
            return this.header;
        }
        getNextNode() {
            if (this.nextNode === null) {
                throw new Error('The current ChainedNode does not have a next node.');
            }
            return this.nextNode;
        }
        getNextNodeAsString() {
            return this.getNextNode().toString();
        }
        getOneAttribute() {
            return this.attributes.getRefOne();
        }
        getOneAttributeAsNumber() {
            return this.getOneAttribute().toNumber();
        }
        getOneAttributeAsNode() {
            return this.getOneAttribute().toNode();
        }
        getOneAttributeAsString() {
            return this.getOneAttribute().toString();
        }
        getReproducingHeader() {
            return ChainedNode.createReproducingString(this.getHeader());
        }
        hasGivenHeader(header) {
            return (this.hasHeader() && this.getHeader() === header);
        }
        hasHeader() {
            return (this.header !== undefined);
        }
        hasNextNode() {
            return (this.nextNode !== undefined);
        }
        toNumber() {
            if (!this.hasHeader() || this.containsAttributes() || this.hasNextNode()) {
                throw new Error('The current ChainedNode does not represent a number.');
            }
            return Number(this.getHeader());
        }
        toNode() {
            if (this.hasNextNode()) {
                throw new Error('The current ChainedNode does not represent a Node.');
            }
            const node = new Node_1.Node();
            if (this.hasHeader()) {
                node.setHeader(this.getHeader());
            }
            for (const a of this.getAttributes()) {
                node.addAttribute(a.toNode());
            }
            return node;
        }
        toString() {
            let string = '';
            if (this.hasHeader()) {
                string += ChainedNode.createReproducingString(this.getHeader());
            }
            if (this.containsAttributes()) {
                string += '(';
                let atBegin = true;
                for (const a of this.getAttributes()) {
                    if (atBegin) {
                        atBegin = false;
                        string += a.toString();
                    }
                    else {
                        string += ',' + a.toString();
                    }
                }
                string += ')';
            }
            if (this.hasNextNode()) {
                string += '.' + this.getNextNode().toString();
            }
            return string;
        }
        toStringInBrackets() {
            return ('(' + this.toString() + ')');
        }
        addAttributeFromNode(attribute) {
            this.attributes.addAtEnd(ChainedNode.fromNode(attribute));
        }
        reset() {
            this.header = undefined;
            this.attributes.clear();
            this.nextNode = undefined;
        }
        resetFromString(string) {
            this.reset();
            if (this.setAndGetNextIndex(string, 0) !== string.length) {
                throw new Error('The given string does not represent a ChainedNode: ' + string);
            }
        }
        setAndGetNextIndex(substring, startIndex) {
            var nextIndex = startIndex;
            var taskAfterSetProbableHeader = Task_1.Task.DO_NOTHING;
            var headerLength = 0;
            while (nextIndex < substring.length) {
                var character = substring.charAt(nextIndex);
                if (character == '(') {
                    taskAfterSetProbableHeader = Task_1.Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE;
                    nextIndex++;
                    break;
                }
                if (character == ',') {
                    break;
                }
                if (character == ')') {
                    break;
                }
                if (character == '.') {
                    taskAfterSetProbableHeader = Task_1.Task.READ_NEXT_NODE;
                    nextIndex++;
                    break;
                }
                nextIndex++;
                headerLength++;
            }
            if (headerLength > 0) {
                this.header = Node_1.Node.createOriginStringFromReproducingString(substring.substring(startIndex, startIndex + headerLength));
            }
            var readNextNode = false;
            switch (taskAfterSetProbableHeader) {
                case Task_1.Task.READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE:
                    const node = new ChainedNode();
                    nextIndex = node.setAndGetNextIndex(substring, nextIndex);
                    this.attributes.addAtEnd(node);
                    while (nextIndex < substring.length) {
                        const character = substring.charAt(nextIndex);
                        if (character == ',') {
                            const node2 = new ChainedNode();
                            nextIndex = node2.setAndGetNextIndex(substring, nextIndex + 1);
                            this.attributes.addAtEnd(node2);
                        }
                        else if (character == ')') {
                            nextIndex++;
                            break;
                        }
                    }
                    if (nextIndex < substring.length - 1 && substring.charAt(nextIndex) == '.') {
                        nextIndex++;
                        readNextNode = true;
                    }
                    break;
                case Task_1.Task.DO_NOTHING:
                    return nextIndex;
                case Task_1.Task.READ_NEXT_NODE:
                    readNextNode = true;
            }
            if (!readNextNode) {
                return nextIndex;
            }
            this.nextNode = new ChainedNode();
            return this.nextNode.setAndGetNextIndex(substring, nextIndex);
        }
        setAttributes(attributes) {
            this.attributes.clear();
            this.attributes.addElementsAtEnd(attributes);
        }
        setAttributesFromNodes(attributes) {
            this.attributes.clear();
            for (const a of attributes) {
                this.addAttributeFromNode(a);
            }
        }
        setHeader(header) {
            if (header === null) {
                throw new Error('The given header is null.');
            }
            if (header === undefined) {
                throw new Error('The given header is undefined.');
            }
            if (header.length == 0) {
                throw new Error('The given header is empty.');
            }
            this.header = header;
        }
        setNextNode(nextNode) {
            if (nextNode === null) {
                throw new Error('The given nextNode is null.');
            }
            if (nextNode === undefined) {
                throw new Error('The given nextNode is undefined.');
            }
            this.nextNode = nextNode;
        }
    }
    ChainedNode.DOT_CODE = '$D';
    ChainedNode.COMMA_CODE = '$M';
    ChainedNode.DOLLAR_SYMBOL_CODE = '$X';
    ChainedNode.OPEN_BRACKET_CODE = '$O';
    ChainedNode.CLOSED_BRACKET_CODE = '$C';
    exports.ChainedNode = ChainedNode;
});
define("Core/GridUniversalAPI/TopLeftPositionedRectangle", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TopLeftPositionedRectangle {
        constructor(xPosition, yPosition, width, height) {
            if (xPosition === null) {
                throw new Error('The given xPosition is null.');
            }
            if (xPosition === undefined) {
                throw new Error('The given xPosition is undefined.');
            }
            if (yPosition === null) {
                throw new Error('The given yPosition is null.');
            }
            if (yPosition === undefined) {
                throw new Error('The given yPosition is undefined.');
            }
            if (width === null) {
                throw new Error('The given width is null.');
            }
            if (width === undefined) {
                throw new Error('The given width is undefined.');
            }
            if (height === null) {
                throw new Error('The given height is null.');
            }
            if (height === undefined) {
                throw new Error('The given height is undefined.');
            }
            if (width < 0) {
                throw new Error('The given width is negative.');
            }
            if (height < 0) {
                throw new Error('The given height is negative.');
            }
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.width = width;
            this.height = height;
        }
        getBottomYPosition() {
            return (this.yPosition + this.height);
        }
        getHeight() {
            return this.height;
        }
        getRightXPosition() {
            return (this.xPosition + this.width);
        }
        getWidth() {
            return this.width;
        }
        getXPosition() {
            return this.xPosition;
        }
        getYPosition() {
            return this.yPosition;
        }
    }
    exports.TopLeftPositionedRectangle = TopLeftPositionedRectangle;
});
define("Core/Math/CentralCalculator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CentralCalculator {
        static getMax(...values) {
            if (values === undefined) {
                throw new Error('The given values is undefined.');
            }
            if (values === null) {
                throw new Error('The given values is empty.');
            }
            if (values.length === 0) {
                throw new Error('There is not given a value.');
            }
            var max = values[0];
            for (const v of values) {
                if (v > max) {
                    max = v;
                }
            }
            return max;
        }
        static getMin(...values) {
            if (values === undefined) {
                throw new Error('The given values is undefined.');
            }
            if (values === null) {
                throw new Error('The given values is empty.');
            }
            if (values.length === 0) {
                throw new Error('There is not given a value.');
            }
            var min = values[0];
            for (const v of values) {
                if (v < min) {
                    min = v;
                }
            }
            return min;
        }
        constructor() { }
    }
    exports.CentralCalculator = CentralCalculator;
});
define("Core/Net/EndPoint2/NetEndPoint2", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint2 {
        constructor(ip, port, optionalTarget) {
            this.onMessage = (messageEvent) => {
                this.receive(messageEvent.data);
            };
            this.onOpen = (event) => {
                console.log("The current NetEndPoint2 has been connected.");
                const targetMessage = (this.target === undefined) ?
                    NetEndPoint2.CLEAR_TARGET_MESSAGE_PREFIX :
                    NetEndPoint2.TARGET_MESSAGE_PREFIX + this.target;
                this.sendRawMessage(targetMessage);
            };
            if (ip === null) {
                throw new Error('The given ip is null.');
            }
            if (ip === undefined) {
                throw new Error('The given ip is undefined.');
            }
            if (ip.length === 0) {
                throw new Error('The given ip is empty.');
            }
            if (port < 0 || port > 65535) {
                throw new Error('The given port is not in [0, 65535].');
            }
            if (optionalTarget.containsAny()) {
                if (optionalTarget.getRefElement().length === 0) {
                    throw new Error('The given target is empty.');
                }
                this.target = optionalTarget.getRefElement();
            }
            else {
                this.target = undefined;
            }
            this.webSocket = new WebSocket('ws://' + ip + ':' + port);
            this.webSocket.onopen = this.onOpen;
            this.webSocket.onmessage = this.onMessage;
        }
        getTarget() {
            if (this.target === undefined) {
                throw new Error('The current NetEndPoint2 does not have a target.');
            }
            return this.target;
        }
        hasReceiver() {
            return (this.receiver !== undefined);
        }
        hasTarget() {
            return (this.target !== undefined);
        }
        send(message) {
            this.sendRawMessage(NetEndPoint2.STANDARD_MESSAGE_PREFIX + message);
        }
        setReceiver(receiver) {
            if (receiver === null) {
                throw new Error('The given receiver is null.');
            }
            if (receiver === undefined) {
                throw new Error('The given receiver is undefined.');
            }
            this.receiver = receiver;
        }
        receive(message) {
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new Error('The given message is undefined.');
            }
            if (message.length === 0) {
                throw new Error('The given message is empty.');
            }
            switch (message[0]) {
                case NetEndPoint2.STANDARD_MESSAGE_PREFIX:
                    if (this.receiver === undefined) {
                        throw new Error('The current NetEndPoint2 does not have a receiver.');
                    }
                    this.receiver(message.substring(1, message.length));
                    break;
                default:
                    throw new Error('The given message is not valid: ' + message);
            }
        }
        sendRawMessage(rawMessage) {
            this.webSocket.send(rawMessage);
        }
    }
    NetEndPoint2.STANDARD_MESSAGE_PREFIX = 'M';
    NetEndPoint2.TARGET_MESSAGE_PREFIX = 'T';
    NetEndPoint2.CLEAR_TARGET_MESSAGE_PREFIX = 'A';
    exports.NetEndPoint2 = NetEndPoint2;
});
define("Core/Net/EndPoint3/MessageRole", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var MessageRole;
    (function (MessageRole) {
        MessageRole[MessageRole["TARGET_MESSAGE"] = 0] = "TARGET_MESSAGE";
        MessageRole[MessageRole["STANDARD_MESSAGE"] = 1] = "STANDARD_MESSAGE";
        MessageRole[MessageRole["REPLY_MESSAGE"] = 2] = "REPLY_MESSAGE";
        MessageRole[MessageRole["ERROR_MESSAGE"] = 3] = "ERROR_MESSAGE";
    })(MessageRole = exports.MessageRole || (exports.MessageRole = {}));
});
define("Core/Net/EndPoint3/Package", ["require", "exports", "Core/Net/EndPoint3/MessageRole"], function (require, exports, MessageRole_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Package {
        constructor(index, messageRole, message) {
            if (index === null) {
                throw new Error('The given index is null.');
            }
            if (index === undefined) {
                throw new Error('The given index is undefined.');
            }
            if (index < 1) {
                throw new Error('The given index is not positive.');
            }
            if (messageRole === null) {
                throw new Error('The given message role is null.');
            }
            if (messageRole === undefined) {
                throw new Error('The given message role is undefined.');
            }
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new Error('The given message is undefined.');
            }
            this.index = index;
            this.message = message;
            this.messageRole = messageRole;
        }
        static createFromString(string) {
            return new Package(Number.parseInt(string.substring(0, 8)), Package.createMessageRole(string.substring(8, 9)), string.substring(9, string.length));
        }
        static createMessageRole(messageRolePrefix) {
            switch (messageRolePrefix) {
                case Package.TARGET_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.TARGET_MESSAGE;
                case Package.STANDARD_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.STANDARD_MESSAGE;
                case Package.REPLY_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.REPLY_MESSAGE;
                case Package.ERROR_MESSAGE_PREFIX:
                    return MessageRole_1.MessageRole.ERROR_MESSAGE;
                default:
                    throw new Error('The given message role prefix is not valid.');
            }
        }
        getIndex() {
            return this.index;
        }
        getMessage() {
            return this.message;
        }
        getMessageRole() {
            return this.messageRole;
        }
        hasIndex(index) {
            return this.index === index;
        }
        toString() {
            return (this.getIndexString() + this.getMessageRolePrefix() + this.message);
        }
        getIndexString() {
            var indexString = this.index.toString();
            while (indexString.length < Package.INDEX_STRING_LENGTH) {
                indexString = '0' + indexString;
            }
            return indexString;
        }
        getMessageRolePrefix() {
            switch (this.messageRole) {
                case MessageRole_1.MessageRole.TARGET_MESSAGE:
                    return Package.TARGET_MESSAGE_PREFIX;
                case MessageRole_1.MessageRole.STANDARD_MESSAGE:
                    return Package.STANDARD_MESSAGE_PREFIX;
                case MessageRole_1.MessageRole.REPLY_MESSAGE:
                    return Package.REPLY_MESSAGE_PREFIX;
                case MessageRole_1.MessageRole.ERROR_MESSAGE:
                    return Package.ERROR_MESSAGE_PREFIX;
                default:
                    throw new Error('There is not defined a prefix for the given message role.');
            }
        }
    }
    Package.TARGET_MESSAGE_PREFIX = 'T';
    Package.STANDARD_MESSAGE_PREFIX = 'M';
    Package.REPLY_MESSAGE_PREFIX = 'R';
    Package.ERROR_MESSAGE_PREFIX = 'E';
    Package.INDEX_STRING_LENGTH = 8;
    exports.Package = Package;
});
define("Core/Net/EndPoint3/NetEndPoint3", ["require", "exports", "Core/Container/LinkedList", "Core/Net/EndPoint3/MessageRole", "Core/Net/EndPoint2/NetEndPoint2", "Core/Net/EndPoint3/Package"], function (require, exports, LinkedList_4, MessageRole_2, NetEndPoint2_1, Package_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint3 {
        constructor(ip, port, optionalTarget) {
            this.messageIndex = 0;
            this.receivedPackages = new LinkedList_4.LinkedList();
            this.receive = (message) => {
                this.receivePackage(Package_1.Package.createFromString(message));
            };
            this.internalNetEndPoint = new NetEndPoint2_1.NetEndPoint2(ip, port, optionalTarget);
            this.internalNetEndPoint.setReceiver(this.receive);
        }
        getTarget() {
            return this.internalNetEndPoint.getTarget();
        }
        hasReceiver() {
            return (this.receiver !== undefined);
        }
        hasTarget() {
            return this.internalNetEndPoint.hasTarget();
        }
        sendAndGetReply(message) {
            const messageIndex = this.getNextMessageIndex();
            this.sendPackage(new Package_1.Package(messageIndex, MessageRole_2.MessageRole.STANDARD_MESSAGE, message));
            return 'Ok';
        }
        setReceiver(receiver) {
            if (receiver === null) {
                throw new Error('The given receiver is null.');
            }
            if (receiver === undefined) {
                throw new Error('The given receiver is null');
            }
            this.receiver = receiver;
        }
        getNextMessageIndex() {
            this.messageIndex++;
            return this.messageIndex;
        }
        receivePackage(package_) {
            switch (package_.getMessageRole()) {
                case MessageRole_2.MessageRole.STANDARD_MESSAGE:
                    if (this.receiver === undefined) {
                        throw new Error('The current NetEndPoint3 does not have a receiver.');
                    }
                    try {
                        this.sendPackage(new Package_1.Package(package_.getIndex(), MessageRole_2.MessageRole.REPLY_MESSAGE, this.receiver(package_.getMessage())));
                    }
                    catch (error) {
                        this.sendPackage(new Package_1.Package(package_.getIndex(), MessageRole_2.MessageRole.ERROR_MESSAGE, 'ERROR_MESSAGE'));
                    }
                    break;
                case MessageRole_2.MessageRole.REPLY_MESSAGE:
                case MessageRole_2.MessageRole.ERROR_MESSAGE:
                    this.receivedPackages.addAtEnd(package_);
                    break;
                default:
                    throw new Error('The received package is not valid.');
            }
        }
        sendPackage(package_) {
            this.internalNetEndPoint.send(package_.toString());
        }
        waitToAndGetAndRemoveReceivedPackage(index) {
            const startTimeInMilliseconds = new Date().getMilliseconds();
            while (new Date().getMilliseconds() - startTimeInMilliseconds < NetEndPoint3.TIMEOUT_IN_MILLISECONDS) {
                const package_ = this.receivedPackages.getRefFirstByConditionOrNull(rp => rp.hasIndex(index));
                if (package_ !== null) {
                    this.receivedPackages.removeFirst2(package_);
                    return package_;
                }
            }
            throw new Error('The current NetEndPoint3 reached the timeout by waiting to a message.');
        }
    }
    NetEndPoint3.TIMEOUT_IN_MILLISECONDS = 5000;
    exports.NetEndPoint3 = NetEndPoint3;
});
define("Core/Net/EndPoint5/IDataProviderController", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Core/Net/EndPoint5/NetEndPoint5Protocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint5Protocol {
    }
    NetEndPoint5Protocol.COMMANDS_HEADER = "Commands";
    NetEndPoint5Protocol.DATA_REQUEST_HEADER = "DataRequest";
    NetEndPoint5Protocol.DATA_HEADER = "Data";
    NetEndPoint5Protocol.DONE_HEADER = "Done";
    NetEndPoint5Protocol.ERROR_HEADER = "Error";
    exports.NetEndPoint5Protocol = NetEndPoint5Protocol;
});
define("Core/Net/EndPoint5/NetEndPoint5", ["require", "exports", "Core/Document/ChainedNode/ChainedNode", "Core/Document/Node/Node", "Core/Container/LinkedList", "Core/Net/EndPoint3/NetEndPoint3", "Core/Net/EndPoint5/NetEndPoint5Protocol"], function (require, exports, ChainedNode_1, Node_2, LinkedList_5, NetEndPoint3_1, NetEndPoint5Protocol_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint5 {
        constructor(ip, port, optionalTarget) {
            this.receiverController = undefined;
            this.receiveMessageAndGetReply = (message) => {
                try {
                    return this.receiveDocumentNodeMessageAndGetReply(ChainedNode_1.ChainedNode.fromString(message));
                }
                catch (exception) {
                    return (NetEndPoint5Protocol_1.NetEndPoint5Protocol.ERROR_HEADER + '(' + Node_2.Node.createReproducingString(exception.getMessage()) + ')');
                }
            };
            this.internalNetEndPoint = new NetEndPoint3_1.NetEndPoint3(ip, port, optionalTarget);
            this.internalNetEndPoint.setReceiver(this.receiveMessageAndGetReply);
        }
        getData(request) {
            const message = 'Data' + request.toStringInBrackets();
            const reply = Node_2.Node.fromString(this.internalNetEndPoint.sendAndGetReply(message));
            switch (reply.getHeader()) {
                case 'Data':
                    return reply.getRefOneAttribute();
                case 'Error':
                    throw new Error(reply.getOneAttributeHeader());
            }
        }
        getTarget() {
            return this.internalNetEndPoint.getTarget();
        }
        hasReceiverController() {
            return (this.receiverController !== undefined);
        }
        run(command) {
            const commands = new LinkedList_5.LinkedList();
            commands.addAtEnd(command);
            this.runCommands(commands);
        }
        runCommands(commands) {
            const message = NetEndPoint5Protocol_1.NetEndPoint5Protocol.COMMANDS_HEADER + commands.toStringInBrackets();
            this.internalNetEndPoint.sendAndGetReply(message);
        }
        setReceiverController(receiverController) {
            if (receiverController === null) {
                throw new Error('The given receiverController is null.');
            }
            if (receiverController === undefined) {
                throw new Error('The given receiverController is undefined.');
            }
            this.receiverController = receiverController;
        }
        getReceiverController() {
            if (!this.hasReceiverController()) {
                throw new Error('The current NetEndPoint5 does not have a recevierController.');
            }
            return this.receiverController;
        }
        receiveDocumentNodeMessageAndGetReply(message) {
            const receiverController = this.getReceiverController();
            switch (message.getHeader()) {
                case NetEndPoint5Protocol_1.NetEndPoint5Protocol.COMMANDS_HEADER:
                    for (const a of message.getAttributes()) {
                        receiverController.run(a);
                    }
                    return NetEndPoint5Protocol_1.NetEndPoint5Protocol.DONE_HEADER;
                case NetEndPoint5Protocol_1.NetEndPoint5Protocol.DATA_REQUEST_HEADER:
                    return (NetEndPoint5Protocol_1.NetEndPoint5Protocol.DATA_HEADER + '(' + receiverController.getData(message.getOneAttribute()) + ')');
                default:
                    throw new Error('The given message is not valid.');
            }
        }
    }
    exports.NetEndPoint5 = NetEndPoint5;
});
define("Core/Testing/BaseTest/BaseTest", ["require", "exports", "Core/Container/LinkedList"], function (require, exports, LinkedList_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BaseTest {
        constructor() {
            this.currentTestCaseErrors = new LinkedList_6.LinkedList();
        }
        run() {
            console.log('Start ' + this.constructor.name);
            for (const tc of this.getRefTestCases()) {
                this.runTestCase(tc);
            }
            console.log();
        }
        addErrorForCurrentTestCase(error) {
            this.currentTestCaseErrors.addAtEnd(error);
        }
        getRefProperties() {
            var properties = [];
            var prototype = this;
            do {
                properties = properties.concat(Object.getOwnPropertyNames(prototype));
            } while (prototype = Object.getPrototypeOf(prototype));
            return properties;
        }
        getRefTestCases() {
            var testCases = [];
            for (const p of this.getRefProperties()) {
                const name = p;
                if (name.startsWith('testCase_')) {
                    testCases = testCases.concat(name);
                }
            }
            return testCases;
        }
        runTestCase(testCase) {
            this.currentTestCaseErrors.clear();
            try {
                this[testCase]();
                if (this.currentTestCaseErrors.isEmpty()) {
                    console.log('   PASSED: ' + testCase);
                }
                else {
                    console.log('-->FAILED: ' + testCase);
                }
            }
            catch (error) {
                console.log('-->FAILED: ' + testCase + ': ' + error);
            }
            var i = 1;
            for (const e of this.currentTestCaseErrors) {
                console.log('   ' + i.toString() + ') ' + e);
                i++;
            }
        }
    }
    exports.BaseTest = BaseTest;
});
define("Core/Testing/Test/NumberMediator", ["require", "exports", "Core/Testing/Test/Mediator"], function (require, exports, Mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NumberMediator extends Mediator_1.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
    }
    exports.NumberMediator = NumberMediator;
});
define("Core/Testing/Test/StringMediator", ["require", "exports", "Core/Testing/Test/Mediator"], function (require, exports, Mediator_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class StringMediator extends Mediator_2.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
        isNotEmpty() {
            if (this.argument.length < 1) {
                this.test.addErrorForCurrentTestCase('A string, that is not empty, was expected, but an empty string was received.');
            }
        }
    }
    exports.StringMediator = StringMediator;
});
define("Core/Testing/Test/Test", ["require", "exports", "Core/Testing/BaseTest/BaseTest", "Core/Testing/Test/FunctionMediator", "Core/Testing/Test/Mediator", "Core/Testing/Test/NumberMediator", "Core/Testing/Test/StringMediator"], function (require, exports, BaseTest_1, FunctionMediator_1, Mediator_3, NumberMediator_1, StringMediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Test extends BaseTest_1.BaseTest {
        expect(argument) {
            if (!argument) {
                this.addErrorForCurrentTestCase('True was expected, but false was received.');
            }
        }
        expectFunction(argument) {
            return new FunctionMediator_1.FunctionMediator(this, argument);
        }
        expectNot(argument) {
            if (argument) {
                this.addErrorForCurrentTestCase('False was expected, but true was received.');
            }
        }
        expectNotThisCase() {
            this.addErrorForCurrentTestCase('The current case was not expected, but reached.');
        }
        expectNumber(argument) {
            return new NumberMediator_1.NumberMediator(this, argument);
        }
        expectObject(argument) {
            return new Mediator_3.Mediator(this, argument);
        }
        expectString(argument) {
            return new StringMediator_1.StringMediator(this, argument);
        }
    }
    exports.Test = Test;
});
define("Core/Testing/Test/Mediator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Mediator {
        constructor(test, argument) {
            if (test === null) {
                throw new Error('The given test is null.');
            }
            if (test === undefined) {
                throw new Error('The given test is undefined.');
            }
            this.test = test;
            this.argument = argument;
        }
        equals(value) {
            if (this.argument !== value) {
                this.test.addErrorForCurrentTestCase('An object that equals ' + value.toString() + ' was expected, but ' + this.argument.toString() + ' was received.');
            }
        }
        fulfills(condition) {
            if (!condition(this.argument)) {
                this.test.addErrorForCurrentTestCase('An object, that fulfills the given condition, was expected, but an object, that does not fulfill it, was received.');
            }
        }
        hasStringRepresentation(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            const actualString = this.argument.toString();
            if (actualString !== string) {
                this.test.addErrorForCurrentTestCase('An object with the string representation \''
                    + string
                    + '\' was expected, but an object with the string representation \''
                    + actualString
                    + '\' was received.');
            }
        }
        isNotNullOrUndefined() {
            if (this.argument === null) {
                this.test.addErrorForCurrentTestCase('An object was expected, but null was received.');
            }
            if (this.argument === undefined) {
                this.test.addErrorForCurrentTestCase('An object was exected, but undefined was received.');
            }
        }
        isSameAs(object) {
            if (!Object.is(this.argument, object)) {
                this.test.addErrorForCurrentTestCase('The given object was expected, but another object was received.');
            }
        }
    }
    exports.Mediator = Mediator;
});
define("Core/Testing/Test/ThrownErrorMediator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ThrownErrorMediator {
        constructor(test, error) {
            if (test === null) {
                throw new Error('The given test is null.');
            }
            if (test === undefined) {
                throw new Error('The given test is undefined.');
            }
            if (error === null) {
                throw new Error('The given error is null.');
            }
            if (error === undefined) {
                throw new Error('The given error is undefined.');
            }
            this.test = test;
            this.error = error;
        }
        withMessage(message) {
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new Error('The given message is undefined.');
            }
            if (this.error.message !== message) {
                this.test.addErrorForCurrentTestCase('An error with the message \''
                    + message
                    + '\' was expected, but an error with the message \''
                    + this.error.message
                    + '\' was received.');
            }
        }
    }
    exports.ThrownErrorMediator = ThrownErrorMediator;
});
define("Core/Testing/Test/FunctionMediator", ["require", "exports", "Core/Testing/Test/Mediator", "Core/Testing/Test/ThrownErrorMediator"], function (require, exports, Mediator_4, ThrownErrorMediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FunctionMediator extends Mediator_4.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
        doesNotThrowError() {
            try {
                this.argument();
            }
            catch (error) {
                this.test.addErrorForCurrentTestCase('There was not expected any error, but there was thrown an error.');
            }
        }
        throwsError() {
            try {
                this.argument();
                this.test.addErrorForCurrentTestCase('An error was expected, but there was not thrown any error.');
            }
            catch (error) {
                return new ThrownErrorMediator_1.ThrownErrorMediator(this.test, error);
            }
        }
    }
    exports.FunctionMediator = FunctionMediator;
});
define("Core/Testing/Test/TestPool", ["require", "exports", "Core/Container/LinkedList"], function (require, exports, LinkedList_7) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TestPool {
        constructor() {
            this.tests = new LinkedList_7.LinkedList();
            this.testPools = new LinkedList_7.LinkedList();
        }
        run() {
            this.tests.forEach(t => t.run());
            this.testPools.forEach(tp => tp.run());
        }
        addTest(test) {
            this.tests.addAtEnd(test);
        }
        addTestPool(testPool) {
            if (testPool.containsRecursively(this)) {
                throw new Error('The given test pool contains the current test pool recursively.');
            }
            this.testPools.addAtEnd(testPool);
        }
        containsRecursively(testPool) {
            if (this.testPools.containsGivenElement(testPool)) {
                return true;
            }
            return this.testPools.contains(ts => ts.containsRecursively(testPool));
        }
    }
    exports.TestPool = TestPool;
});
define("System/GUI/Input/Key", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var Key;
    (function (Key) {
        Key[Key["A"] = 0] = "A";
        Key[Key["B"] = 1] = "B";
        Key[Key["C"] = 2] = "C";
        Key[Key["D"] = 3] = "D";
        Key[Key["E"] = 4] = "E";
        Key[Key["F"] = 5] = "F";
        Key[Key["G"] = 6] = "G";
        Key[Key["H"] = 7] = "H";
        Key[Key["I"] = 8] = "I";
        Key[Key["J"] = 9] = "J";
        Key[Key["K"] = 10] = "K";
        Key[Key["L"] = 11] = "L";
        Key[Key["M"] = 12] = "M";
        Key[Key["N"] = 13] = "N";
        Key[Key["O"] = 14] = "O";
        Key[Key["P"] = 15] = "P";
        Key[Key["Q"] = 16] = "Q";
        Key[Key["R"] = 17] = "R";
        Key[Key["S"] = 18] = "S";
        Key[Key["T"] = 19] = "T";
        Key[Key["U"] = 20] = "U";
        Key[Key["V"] = 21] = "V";
        Key[Key["W"] = 22] = "W";
        Key[Key["X"] = 23] = "X";
        Key[Key["Y"] = 24] = "Y";
        Key[Key["Z"] = 25] = "Z";
        Key[Key["AE"] = 26] = "AE";
        Key[Key["OE"] = 27] = "OE";
        Key[Key["UE"] = 28] = "UE";
        Key[Key["NUMBER_0"] = 29] = "NUMBER_0";
        Key[Key["NUMBER_1"] = 30] = "NUMBER_1";
        Key[Key["NUMBER_2"] = 31] = "NUMBER_2";
        Key[Key["NUMBER_3"] = 32] = "NUMBER_3";
        Key[Key["NUMBER_4"] = 33] = "NUMBER_4";
        Key[Key["NUMBER_5"] = 34] = "NUMBER_5";
        Key[Key["NUMBER_6"] = 35] = "NUMBER_6";
        Key[Key["NUMBER_7"] = 36] = "NUMBER_7";
        Key[Key["NUMBER_8"] = 37] = "NUMBER_8";
        Key[Key["NUMBER_9"] = 38] = "NUMBER_9";
        Key[Key["NUMBERPAD_0"] = 39] = "NUMBERPAD_0";
        Key[Key["NUMBERPAD_1"] = 40] = "NUMBERPAD_1";
        Key[Key["NUMBERPAD_2"] = 41] = "NUMBERPAD_2";
        Key[Key["NUMBERPAD_3"] = 42] = "NUMBERPAD_3";
        Key[Key["NUMBERPAD_4"] = 43] = "NUMBERPAD_4";
        Key[Key["NUMBERPAD_5"] = 44] = "NUMBERPAD_5";
        Key[Key["NUMBERPAD_6"] = 45] = "NUMBERPAD_6";
        Key[Key["NUMBERPAD_7"] = 46] = "NUMBERPAD_7";
        Key[Key["NUMBERPAD_8"] = 47] = "NUMBERPAD_8";
        Key[Key["NUMBERPAD_9"] = 48] = "NUMBERPAD_9";
        Key[Key["F1"] = 49] = "F1";
        Key[Key["F2"] = 50] = "F2";
        Key[Key["F3"] = 51] = "F3";
        Key[Key["F4"] = 52] = "F4";
        Key[Key["F5"] = 53] = "F5";
        Key[Key["F6"] = 54] = "F6";
        Key[Key["F7"] = 55] = "F7";
        Key[Key["F8"] = 56] = "F8";
        Key[Key["F9"] = 57] = "F9";
        Key[Key["F10"] = 58] = "F10";
        Key[Key["F11"] = 59] = "F11";
        Key[Key["F12"] = 60] = "F12";
        Key[Key["ARROW_LEFT"] = 61] = "ARROW_LEFT";
        Key[Key["ARROW_RIGHT"] = 62] = "ARROW_RIGHT";
        Key[Key["ARROW_UP"] = 63] = "ARROW_UP";
        Key[Key["ARROW_DOWN"] = 64] = "ARROW_DOWN";
        Key[Key["ALTERNATIVE"] = 65] = "ALTERNATIVE";
        Key[Key["BACKSPACE"] = 66] = "BACKSPACE";
        Key[Key["BREAK"] = 67] = "BREAK";
        Key[Key["CAPS_LOCK"] = 68] = "CAPS_LOCK";
        Key[Key["COMMA"] = 69] = "COMMA";
        Key[Key["CONTROL"] = 70] = "CONTROL";
        Key[Key["DELETE"] = 71] = "DELETE";
        Key[Key["DOLLAR_SYMBOL"] = 72] = "DOLLAR_SYMBOL";
        Key[Key["DOT"] = 73] = "DOT";
        Key[Key["END"] = 74] = "END";
        Key[Key["ENTER"] = 75] = "ENTER";
        Key[Key["ESCAPE"] = 76] = "ESCAPE";
        Key[Key["EXCLAMATION_MARK"] = 77] = "EXCLAMATION_MARK";
        Key[Key["GRADE_SYMBOL"] = 78] = "GRADE_SYMBOL";
        Key[Key["GRAVIS"] = 79] = "GRAVIS";
        Key[Key["HOME"] = 80] = "HOME";
        Key[Key["HYPHEN"] = 81] = "HYPHEN";
        Key[Key["INSERT"] = 82] = "INSERT";
        Key[Key["MENU"] = 83] = "MENU";
        Key[Key["NUMBER_LOCK"] = 84] = "NUMBER_LOCK";
        Key[Key["PAGE_DOWN"] = 85] = "PAGE_DOWN";
        Key[Key["PAGE_UP"] = 86] = "PAGE_UP";
        Key[Key["PRINT_SCREEN"] = 87] = "PRINT_SCREEN";
        Key[Key["QUESTION_MARK"] = 88] = "QUESTION_MARK";
        Key[Key["RELATIONS"] = 89] = "RELATIONS";
        Key[Key["SCROLL_LOCK"] = 90] = "SCROLL_LOCK";
        Key[Key["SHIFT"] = 91] = "SHIFT";
        Key[Key["SPACE"] = 92] = "SPACE";
        Key[Key["TABULATOR"] = 93] = "TABULATOR";
        Key[Key["WINDOWS"] = 94] = "WINDOWS";
    })(Key = exports.Key || (exports.Key = {}));
});
define("SystemAPI/GUIAPI/ProcessProperty/RotationDirection", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var RotationDirection;
    (function (RotationDirection) {
        RotationDirection[RotationDirection["FORWARD"] = 0] = "FORWARD";
        RotationDirection[RotationDirection["BACKWARD"] = 1] = "BACKWARD";
    })(RotationDirection = exports.RotationDirection || (exports.RotationDirection = {}));
});
define("SystemAPI/GUIAPI/InputAPI/IInputTaker", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("System/Element/Element", ["require", "exports", "Core/Document/Node/Node"], function (require, exports, Node_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Element {
        getSpecification() {
            return this.getSpecificationAs(this.getType());
        }
        getSpecificationAs(type) {
            return Node_3.Node.withHeaderAndAttributes(type, this.getAttributes());
        }
        toString() {
            return this.getSpecification().toString();
        }
    }
    exports.Element = Element;
});
define("System/GUI/Input/Input", ["require", "exports", "System/Element/Element"], function (require, exports, Element_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Input extends Element_1.Element {
    }
    exports.Input = Input;
});
define("System/GUI/Input/KeyInputType", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var KeyInputType;
    (function (KeyInputType) {
        KeyInputType[KeyInputType["PRESS"] = 0] = "PRESS";
        KeyInputType[KeyInputType["RELEASE"] = 1] = "RELEASE";
        KeyInputType[KeyInputType["TYPING"] = 2] = "TYPING";
    })(KeyInputType = exports.KeyInputType || (exports.KeyInputType = {}));
});
define("System/GUI/Input/KeyInput", ["require", "exports", "System/GUI/Input/Input", "System/GUI/Input/Key", "System/GUI/Input/KeyInputType", "Core/Container/LinkedList", "Core/Document/Node/Node"], function (require, exports, Input_1, Key_1, KeyInputType_1, LinkedList_8, Node_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class KeyInput extends Input_1.Input {
        constructor(key, inputType) {
            super();
            if (key === null) {
                throw new Error('The given key is null.');
            }
            if (key === undefined) {
                throw new Error('The given key is undefined.');
            }
            if (inputType === null) {
                throw new Error('The given inputType is null.');
            }
            if (inputType === undefined) {
                throw new Error('The given inputType is undefined.');
            }
            this.key = key;
            this.inputType = inputType;
        }
        getAttributes() {
            const attributes = new LinkedList_8.LinkedList();
            attributes.addAtEnd(Node_4.Node.withHeaderAndAttribute(KeyInput.KEY_HEADER, Node_4.Node.withHeader(Key_1.Key[this.key])));
            attributes.addAtEnd(Node_4.Node.withHeaderAndAttribute(KeyInput.INPUT_TYPE_HEADER, Node_4.Node.withHeader(KeyInputType_1.KeyInputType[this.inputType])));
            return attributes;
        }
        getType() {
            return KeyInput.TYPE_HEADER;
        }
    }
    KeyInput.TYPE_HEADER = 'KeyInput';
    KeyInput.KEY_HEADER = 'Key';
    KeyInput.INPUT_TYPE_HEADER = 'InputType';
    exports.KeyInput = KeyInput;
});
define("System/GUI/Input/MouseInputType", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var MouseInputType;
    (function (MouseInputType) {
        MouseInputType[MouseInputType["MOUSE_MOVE"] = 0] = "MOUSE_MOVE";
        MouseInputType[MouseInputType["LEFT_MOUSE_BUTTON_PRESS"] = 1] = "LEFT_MOUSE_BUTTON_PRESS";
        MouseInputType[MouseInputType["LEFT_MOUSE_BUTTON_RELEASE"] = 2] = "LEFT_MOUSE_BUTTON_RELEASE";
        MouseInputType[MouseInputType["LEFT_MOUSE_BUTTON_CLICK"] = 3] = "LEFT_MOUSE_BUTTON_CLICK";
        MouseInputType[MouseInputType["RIGHT_MOUSE_BUTTON_PRESS"] = 4] = "RIGHT_MOUSE_BUTTON_PRESS";
        MouseInputType[MouseInputType["RIGHT_MOUSE_BUTTON_RELEASE"] = 5] = "RIGHT_MOUSE_BUTTON_RELEASE";
        MouseInputType[MouseInputType["RIGHT_MOUSE_BUTTON_CLICK"] = 6] = "RIGHT_MOUSE_BUTTON_CLICK";
        MouseInputType[MouseInputType["MOUSE_WHEEL_PRESS"] = 7] = "MOUSE_WHEEL_PRESS";
        MouseInputType[MouseInputType["MOUSE_WHEEL_RELEASE"] = 8] = "MOUSE_WHEEL_RELEASE";
        MouseInputType[MouseInputType["MOUSE_WHEEL_CLICK"] = 9] = "MOUSE_WHEEL_CLICK";
        MouseInputType[MouseInputType["FORWARD_MOUSE_WHEEL_ROTATION_STEP"] = 10] = "FORWARD_MOUSE_WHEEL_ROTATION_STEP";
        MouseInputType[MouseInputType["BACKWARD_MOUSE_WHEEL_ROTATION_STEP"] = 11] = "BACKWARD_MOUSE_WHEEL_ROTATION_STEP";
    })(MouseInputType = exports.MouseInputType || (exports.MouseInputType = {}));
});
define("System/GUI/Input/MouseInput", ["require", "exports", "System/GUI/Input/Input", "Core/Container/LinkedList", "System/GUI/Input/MouseInputType", "Core/Document/Node/Node", "Core/Constant/PascalCaseNameCatalogue"], function (require, exports, Input_2, LinkedList_9, MouseInputType_1, Node_5, PascalCaseNameCatalogue_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class MouseInput extends Input_2.Input {
        constructor(mouseInputType, cursorXPosition, cursorYPosition) {
            super();
            if (mouseInputType === null) {
                throw new Error('The given mouseInputType is null.');
            }
            if (mouseInputType === undefined) {
                throw new Error('The given mouseInputType is undefined.');
            }
            if (cursorXPosition === null) {
                throw new Error('The given cursorXPosition is null.');
            }
            if (cursorXPosition === undefined) {
                throw new Error('The given cursorXPosition is undefined.');
            }
            if (cursorYPosition === null) {
                throw new Error('The given cursorYPosition is null.');
            }
            if (cursorYPosition === undefined) {
                throw new Error('The given cursorYPosition is undefined.');
            }
            this.mouseInputType = mouseInputType;
            this.cursorXPosition = cursorXPosition;
            this.cursorYPosition = cursorYPosition;
        }
        getAttributes() {
            const attributes = new LinkedList_9.LinkedList();
            attributes.addAtEnd(this.getInputTypeSpecification());
            attributes.addAtEnd(this.getCursorPositionSpecification());
            return attributes;
        }
        getCursorXPosition() {
            return this.cursorXPosition;
        }
        getCursorYPosition() {
            return this.cursorYPosition;
        }
        getType() {
            return MouseInput.TYPE_NAME;
        }
        toEnum() {
            return this.mouseInputType;
        }
        getCursorPositionSpecification() {
            const sizeSpecification = Node_5.Node.withHeader(PascalCaseNameCatalogue_1.PascalCaseNameCatalogue.CURSOR_POSITION);
            sizeSpecification.addAttributeFromNumber(this.cursorXPosition);
            sizeSpecification.addAttributeFromNumber(this.cursorYPosition);
            return sizeSpecification;
        }
        getInputTypeSpecification() {
            return Node_5.Node.withHeaderAndAttribute(MouseInput.INPUT_TYPE_HEADER, Node_5.Node.withHeader(MouseInputType_1.MouseInputType[this.mouseInputType]));
        }
    }
    MouseInput.TYPE_NAME = 'MouseInput';
    MouseInput.INPUT_TYPE_HEADER = 'InputType';
    exports.MouseInput = MouseInput;
});
define("System/GUI/Input/MouseInputTypeMapper", ["require", "exports", "System/GUI/Input/MouseInputType", "SystemAPI/GUIAPI/ProcessProperty/RotationDirection"], function (require, exports, MouseInputType_2, RotationDirection_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class MouseInputTypeMapper {
        static createMouseInputTypeFromRotationDirection(rotationDirection) {
            switch (rotationDirection) {
                case RotationDirection_1.RotationDirection.FORWARD:
                    return MouseInputType_2.MouseInputType.FORWARD_MOUSE_WHEEL_ROTATION_STEP;
                case RotationDirection_1.RotationDirection.BACKWARD:
                    return MouseInputType_2.MouseInputType.BACKWARD_MOUSE_WHEEL_ROTATION_STEP;
            }
        }
        constructor() { }
    }
    exports.MouseInputTypeMapper = MouseInputTypeMapper;
});
define("System/GUI/Input/ResizeInput", ["require", "exports", "System/GUI/Input/Input", "Core/Container/LinkedList", "Core/Document/Node/Node", "Core/Constant/PascalCaseNameCatalogue"], function (require, exports, Input_3, LinkedList_10, Node_6, PascalCaseNameCatalogue_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ResizeInput extends Input_3.Input {
        constructor(viewAreaWidth, viewAreaHeight) {
            super();
            if (viewAreaWidth === null) {
                throw new Error('The given viewAreaWidth is null.');
            }
            if (viewAreaWidth === undefined) {
                throw new Error('The given viewAreaWidth is undefined.');
            }
            if (viewAreaWidth < 0) {
                throw new Error('The given viewAreaWidth is negative.');
            }
            if (viewAreaHeight === null) {
                throw new Error('The given viewAreaHeight is null.');
            }
            if (viewAreaHeight === undefined) {
                throw new Error('The given viewAreaHeight is undefined.');
            }
            if (viewAreaHeight < 0) {
                throw new Error('The given viewAreaHeight is negative.');
            }
            this.viewAreaWidth = viewAreaWidth;
            this.viewAreaHeight = viewAreaHeight;
        }
        getAttributes() {
            return LinkedList_10.LinkedList.withElement(this.getSizeSpecification());
        }
        getType() {
            return ResizeInput.TYPE_NAME;
        }
        getViewAreaHeight() {
            return this.viewAreaHeight;
        }
        getViewAreaWidth() {
            return this.viewAreaWidth;
        }
        getSizeSpecification() {
            const sizeSpecification = Node_6.Node.withHeader(PascalCaseNameCatalogue_2.PascalCaseNameCatalogue.SIZE);
            sizeSpecification.addAttributeFromNumber(this.viewAreaWidth);
            sizeSpecification.addAttributeFromNumber(this.viewAreaHeight);
            return sizeSpecification;
        }
    }
    ResizeInput.TYPE_NAME = 'ResizeInput';
    exports.ResizeInput = ResizeInput;
});
define("System/Application/GUIApplication/FrontCanvasGUIClientInputTaker", ["require", "exports", "System/GUI/Input/KeyInput", "System/GUI/Input/KeyInputType", "System/GUI/Input/MouseInput", "System/GUI/Input/MouseInputType", "System/GUI/Input/MouseInputTypeMapper", "System/GUI/Input/ResizeInput"], function (require, exports, KeyInput_1, KeyInputType_2, MouseInput_1, MouseInputType_3, MouseInputTypeMapper_1, ResizeInput_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClientInputTaker {
        constructor(inputTaker, cursorXPositionOnViewAreaGetter, cursorYPositionOnViewAreaGetter) {
            if (inputTaker === null) {
                throw new Error('The given inputTaker is null.');
            }
            if (inputTaker === undefined) {
                throw new Error('The given inputTaker is undefined.');
            }
            if (cursorXPositionOnViewAreaGetter === null) {
                throw new Error('The given cursorXPositionOnViewAreaGetter is null.');
            }
            if (cursorXPositionOnViewAreaGetter === undefined) {
                throw new Error('The given cursorXPositionOnViewAreaGetter is undefined.');
            }
            if (cursorYPositionOnViewAreaGetter === null) {
                throw new Error('The given cursorYPositionOnViewAreaGetter is null.');
            }
            if (cursorYPositionOnViewAreaGetter === undefined) {
                throw new Error('The given cursorYPositionOnViewAreaGetter is undefined.');
            }
            this.inputTaker = inputTaker;
            this.cursorXPositionOnViewAreaGetter = cursorXPositionOnViewAreaGetter;
            this.cursorYPositionOnViewAreaGetter = cursorYPositionOnViewAreaGetter;
        }
        noteKeyPress(key) {
            this.inputTaker(new KeyInput_1.KeyInput(key, KeyInputType_2.KeyInputType.PRESS));
        }
        noteKeyRelease(key) {
            this.inputTaker(new KeyInput_1.KeyInput(key, KeyInputType_2.KeyInputType.RELEASE));
        }
        noteKeyTyping(key) {
            this.inputTaker(new KeyInput_1.KeyInput(key, KeyInputType_2.KeyInputType.TYPING));
        }
        noteLeftMouseButtonClick() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.LEFT_MOUSE_BUTTON_CLICK, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteLeftMouseButtonPress() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.LEFT_MOUSE_BUTTON_PRESS, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteLeftMouseButtonRelease() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.LEFT_MOUSE_BUTTON_RELEASE, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.MOUSE_MOVE, cursorXPositionOnViewArea, cursorYPositionOnViewArea));
        }
        noteMouseWheelClick() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.MOUSE_WHEEL_CLICK, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteMouseWheelPress() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.MOUSE_WHEEL_PRESS, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteMouseWheelRelease() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.MOUSE_WHEEL_RELEASE, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteMouseWheelRotationStep(rotationDirection) {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputTypeMapper_1.MouseInputTypeMapper.createMouseInputTypeFromRotationDirection(rotationDirection), this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteResize(viewAreaWidth, viewAreaHeight) {
            this.inputTaker(new ResizeInput_1.ResizeInput(viewAreaWidth, viewAreaHeight));
        }
        noteRightMouseButtonClick() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.RIGHT_MOUSE_BUTTON_CLICK, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteRightMouseButtonPress() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.RIGHT_MOUSE_BUTTON_PRESS, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
        noteRightMouseButtonRelease() {
            this.inputTaker(new MouseInput_1.MouseInput(MouseInputType_3.MouseInputType.RIGHT_MOUSE_BUTTON_RELEASE, this.cursorXPositionOnViewAreaGetter(), this.cursorYPositionOnViewAreaGetter()));
        }
    }
    exports.FrontCanvasGUIClientInputTaker = FrontCanvasGUIClientInputTaker;
});
define("System/Application/GUIApplication/FrontCanvasGUIClientCommandProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClientCommandProtocol {
        constructor() { }
    }
    FrontCanvasGUIClientCommandProtocol.NOTE_INPUT = 'NoteInput';
    FrontCanvasGUIClientCommandProtocol.OPEN_NEW_TAB = 'OpenNewTab';
    FrontCanvasGUIClientCommandProtocol.RECEIVE_OPTIONAL_FILE = 'ReceiveOptionalFile';
    FrontCanvasGUIClientCommandProtocol.REDIRECT = "Redirect";
    FrontCanvasGUIClientCommandProtocol.REGISTER_IMAGE = 'RegisterImage';
    FrontCanvasGUIClientCommandProtocol.SEND_OPTIONAL_FILE = 'SendOptionalFile';
    FrontCanvasGUIClientCommandProtocol.SET_CURSOR_ICON = 'SetCursorIcon';
    FrontCanvasGUIClientCommandProtocol.SET_ICON = 'SetIcon';
    FrontCanvasGUIClientCommandProtocol.SET_PAINT_COMMANDS = 'SetPaintCommands';
    FrontCanvasGUIClientCommandProtocol.SET_TITLE = "SetTitle";
    exports.FrontCanvasGUIClientCommandProtocol = FrontCanvasGUIClientCommandProtocol;
});
define("System/Application/GUIApplication/FrontCanvasGUIClientObjectProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClientObjectProtocol {
        constructor() { }
    }
    FrontCanvasGUIClientObjectProtocol.CLIP_BOARD_TEXT = 'ClipBoardText';
    FrontCanvasGUIClientObjectProtocol.GUI = 'GUI';
    FrontCanvasGUIClientObjectProtocol.URL = 'URL';
    FrontCanvasGUIClientObjectProtocol.VIEW_AREA_SIZE = 'ViewAreaSize';
    exports.FrontCanvasGUIClientObjectProtocol = FrontCanvasGUIClientObjectProtocol;
});
define("System/GUI/CanvasGUI/CanvasGUICommandProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUICommandProtocol {
    }
    CanvasGUICommandProtocol.PAINT_FILLED_RECTANGLE = 'PaintFilledRectangle';
    CanvasGUICommandProtocol.PAINT_IMAGE = 'PaintImage';
    CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID = 'PaintImageById';
    CanvasGUICommandProtocol.PAINT_TEXT = 'PaintText';
    CanvasGUICommandProtocol.SET_COLOR = 'SetColor';
    CanvasGUICommandProtocol.SET_COLOR_GRADIENT = 'SetColorGradient';
    CanvasGUICommandProtocol.SET_OPACITY_PERCENTAGE = "SetOpacityPercentage";
    CanvasGUICommandProtocol.TRANSLATE = 'Translate';
    exports.CanvasGUICommandProtocol = CanvasGUICommandProtocol;
});
define("System/GUI/CanvasGUI/CanvasGUIObjectProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUIObjectProtocol {
    }
    CanvasGUIObjectProtocol.CREATE_PAINTER = 'CreatePainter';
    exports.CanvasGUIObjectProtocol = CanvasGUIObjectProtocol;
});
define("System/GUI/Color/Color", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Color {
        constructor(redValue, greenValue, blueValue, alphaValue) {
            if (redValue < 0 || redValue > 255) {
                throw new Error('The given redValue is not valid.');
            }
            if (greenValue < 0 || greenValue > 255) {
                throw new Error('The given greenValue is not valid.');
            }
            if (blueValue < 0 || blueValue > 255) {
                throw new Error('The given blueValue is not valid.');
            }
            if (alphaValue < 0 || alphaValue > 255) {
                throw new Error('The given blueValue is not valid.');
            }
            this.redValue = redValue;
            this.greenValue = greenValue;
            this.blueValue = blueValue;
            this.alphaValue = alphaValue;
        }
        static fromSpecification(specification) {
            return Color.fromString(specification.getOneAttributeHeader());
        }
        static fromString(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            switch (string.length) {
                case 8:
                    return new Color(Number.parseInt('0x' + string.substr(2, 2)), Number.parseInt('0x' + string.substr(4, 2)), Number.parseInt('0x' + string.substr(6, 2)), 255);
                case 10:
                    return new Color(Number.parseInt('0x' + string.substr(2, 2)), Number.parseInt('0x' + string.substr(4, 2)), Number.parseInt('0x' + string.substr(6, 2)), Number.parseInt('0x' + string.substr(8, 2)));
                default:
                    throw new Error('The given string is not valid.');
            }
        }
        getAlphaValue() {
            return this.alphaValue;
        }
        getBlueValue() {
            return this.blueValue;
        }
        getGreenValue() {
            return this.greenValue;
        }
        getHTMLCode() {
            return '#'
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.redValue)
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.greenValue)
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.blueValue);
        }
        getRedValue() {
            return this.redValue;
        }
        getNumberAsHexadecimalStringWithLeadingZeros(number) {
            var string = number.toString(16).toUpperCase();
            while (string.length < 2) {
                string = '0' + string;
            }
            return string;
        }
    }
    Color.BLACK = new Color(0, 0, 0, 255);
    Color.WHITE = new Color(255, 255, 255, 255);
    exports.Color = Color;
});
define("SystemAPI/GUIAPI/StructureProperty/DirectionInRectangle", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var DirectionInRectangle;
    (function (DirectionInRectangle) {
        DirectionInRectangle[DirectionInRectangle["HORIZONTAL"] = 0] = "HORIZONTAL";
        DirectionInRectangle[DirectionInRectangle["VERTICAL"] = 1] = "VERTICAL";
        DirectionInRectangle[DirectionInRectangle["DIAGONAL_UP"] = 2] = "DIAGONAL_UP";
        DirectionInRectangle[DirectionInRectangle["DIAGONAL_DOWN"] = 3] = "DIAGONAL_DOWN";
    })(DirectionInRectangle = exports.DirectionInRectangle || (exports.DirectionInRectangle = {}));
});
define("System/GUI/Color/ColorGradient", ["require", "exports", "System/GUI/Color/Color", "SystemAPI/GUIAPI/StructureProperty/DirectionInRectangle"], function (require, exports, Color_1, DirectionInRectangle_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ColorGradient {
        static fromSpecification(specification) {
            return new ColorGradient(DirectionInRectangle_1.DirectionInRectangle[specification.getRefAttributeAtIndex(1).getHeader()], Color_1.Color.fromString(specification.getRefAttributeAtIndex(2).getHeader()), Color_1.Color.fromString(specification.getRefAttributeAtIndex(3).getHeader()));
        }
        static withDirectionAndColor1AndColor2(direction, color1, color2) {
            return new ColorGradient(direction, color1, color2);
        }
        constructor(direction, color1, color2) {
            if (direction === null) {
                throw new Error('The given direction is null.');
            }
            if (direction === undefined) {
                throw new Error('The given direction is undefined.');
            }
            if (color1 === null) {
                throw new Error('The given color1 is null.');
            }
            if (color1 === undefined) {
                throw new Error('The given color1 is undefined.');
            }
            if (color2 === null) {
                throw new Error('The given color2 is null.');
            }
            if (color2 === undefined) {
                throw new Error('The given color2 is undefined.');
            }
            this.direction = direction;
            this.color1 = color1;
            this.color2 = color2;
        }
        getColor1() {
            return this.color1;
        }
        getColor2() {
            return this.color2;
        }
        getDirection() {
            return this.direction;
        }
    }
    exports.ColorGradient = ColorGradient;
});
define("System/GUI/TextFormat/FontType", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var FontType;
    (function (FontType) {
        FontType[FontType["ARIAL"] = 0] = "ARIAL";
        FontType[FontType["ARIAL_BLACK"] = 1] = "ARIAL_BLACK";
        FontType[FontType["COMIC_SANS_MS"] = 2] = "COMIC_SANS_MS";
        FontType[FontType["IMPACT"] = 3] = "IMPACT";
        FontType[FontType["LUCIDA_CONSOLE"] = 4] = "LUCIDA_CONSOLE";
        FontType[FontType["PAPYRUS"] = 5] = "PAPYRUS";
        FontType[FontType["TAHOMA"] = 6] = "TAHOMA";
        FontType[FontType["VERDANA"] = 7] = "VERDANA";
    })(FontType = exports.FontType || (exports.FontType = {}));
});
define("System/GUI/TextFormat/Font", ["require", "exports", "Core/Constant/FontCodeCatalogue", "System/GUI/TextFormat/FontType"], function (require, exports, FontCodeCatalogue_1, FontType_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Font {
        static fromSpecification(fontSpecification) {
            return new Font(FontType_1.FontType[fontSpecification.getOneAttributeHeader()]);
        }
        constructor(fontType) {
            if (fontType === null) {
                throw new Error('The given fontType is null.');
            }
            if (fontType === undefined) {
                throw new Error('The given fontType is undefined.');
            }
            this.fontType = fontType;
        }
        getCode() {
            switch (this.fontType) {
                case FontType_1.FontType.ARIAL:
                    return FontCodeCatalogue_1.FontCodeCatalogue.ARIAL;
                case FontType_1.FontType.ARIAL_BLACK:
                    return FontCodeCatalogue_1.FontCodeCatalogue.ARIAL_BLACK;
                case FontType_1.FontType.COMIC_SANS_MS:
                    return FontCodeCatalogue_1.FontCodeCatalogue.COMIC_SANS_MS;
                case FontType_1.FontType.IMPACT:
                    return FontCodeCatalogue_1.FontCodeCatalogue.IMPACT;
                case FontType_1.FontType.LUCIDA_CONSOLE:
                    return FontCodeCatalogue_1.FontCodeCatalogue.LUCIDA_CONSOLE;
                case FontType_1.FontType.PAPYRUS:
                    return FontCodeCatalogue_1.FontCodeCatalogue.PAPYRUS;
                case FontType_1.FontType.TAHOMA:
                    return FontCodeCatalogue_1.FontCodeCatalogue.TAHOMA;
                case FontType_1.FontType.VERDANA:
                    return FontCodeCatalogue_1.FontCodeCatalogue.VERDANA;
            }
        }
        getFontType() {
            return this.fontType;
        }
    }
    exports.Font = Font;
});
define("System/GUI/Graphic/IImage", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("System/GUI/TextFormat/TextFormat", ["require", "exports", "System/GUI/Color/Color", "System/GUI/TextFormat/Font"], function (require, exports, Color_2, Font_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TextFormat {
        static fromSpecification(specification) {
            const attributes = specification.getRefAttributes();
            return new TextFormat(Font_1.Font.fromSpecification(attributes.getRefAt(1)), attributes.getRefAt(4).getOneAttributeAsNumber(), Color_2.Color.fromSpecification(attributes.getRefAt(5)));
        }
        constructor(textFont, textSize, textColor) {
            if (textFont === null) {
                throw new Error('The given textFont is null.');
            }
            if (textFont === undefined) {
                throw new Error('The given textFont is undefined.');
            }
            if (textSize === null) {
                throw new Error('The given textSize is null.');
            }
            if (textSize === undefined) {
                throw new Error('The given textSize is undefined.');
            }
            if (textSize < 1) {
                throw new Error('The given textSize is not positive.');
            }
            if (textColor === null) {
                throw new Error('The given textColor is null.');
            }
            if (textColor === undefined) {
                throw new Error('The given textColor is undefined.');
            }
            this.textFont = textFont;
            this.textSize = textSize;
            this.textColor = textColor;
        }
        getTextColor() {
            return this.textColor;
        }
        getTextFont() {
            return this.textFont;
        }
        getTextFontCode() {
            return this.textFont.getCode();
        }
        getTextSize() {
            return this.textSize;
        }
    }
    exports.TextFormat = TextFormat;
});
define("System/GUI/CanvasGUI/CanvasGUIGlobalPainter", ["require", "exports", "System/GUI/Color/Color", "SystemAPI/GUIAPI/StructureProperty/DirectionInRectangle", "System/GUI/TextFormat/Font", "System/GUI/TextFormat/FontType", "System/GUI/TextFormat/TextFormat"], function (require, exports, Color_3, DirectionInRectangle_2, Font_2, FontType_2, TextFormat_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUIGlobalPainter {
        constructor(imageCache, canvasRenderingContext) {
            if (imageCache === null) {
                throw new Error('The given imageCache is null.');
            }
            if (imageCache === undefined) {
                throw new Error('The given imageCache is undefined.');
            }
            if (canvasRenderingContext === null) {
                throw new Error('The given canvasRenderingContext is null.');
            }
            if (canvasRenderingContext === undefined) {
                throw new Error('The given canvasRenderingContext is undefined.');
            }
            this.imageCache = imageCache;
            this.canvasRenderingContext = canvasRenderingContext;
        }
        hasGivenClipArea(clipArea) {
            return (this.currentClipArea === clipArea);
        }
        paintFilledRectangle(xPosition, yPosition, width, height) {
            this.canvasRenderingContext.fillRect(xPosition, yPosition, width, height);
        }
        paintFilledRectangleWithColorGradient(xPosition, yPosition, width, height, colorGradient) {
            this.canvasRenderingContext.fillStyle = this.createLinearGradient(xPosition, yPosition, width, height, colorGradient);
            this.canvasRenderingContext.fillRect(xPosition, yPosition, width, height);
        }
        paintImage(xPosition, yPosition, image) {
            this.paintImageWithSize(xPosition, yPosition, image, image.getWidth(), image.getHeight());
        }
        paintImageById(xPosition, yPosition, id) {
            if (this.imageCache.containsWithId(id)) {
                this.paintImage(xPosition, yPosition, this.imageCache.getRefById(id));
            }
        }
        paintImageByIdWithSize(xPosition, yPosition, id, width, height) {
            if (this.imageCache.containsWithId(id)) {
                this.paintImageWithSize(xPosition, yPosition, this.imageCache.getRefById(id), width, height);
            }
        }
        paintImageWithSize(xPosition, yPosition, image, width, height) {
            this.canvasRenderingContext.drawImage(image.toCanvas(), xPosition, yPosition, width, height);
        }
        paintText(xPosition, yPosition, text) {
            this.paintTextWithTextFormat(xPosition, yPosition, text, CanvasGUIGlobalPainter.DEFAULT_TEXT_FORMAT);
        }
        paintTextWithTextFormat(xPosition, yPosition, text, textFormat) {
            this.canvasRenderingContext.textBaseline = 'top';
            this.canvasRenderingContext.font = textFormat.getTextSize() + 'px ' + textFormat.getTextFontCode();
            this.canvasRenderingContext.fillStyle = textFormat.getTextColor().getHTMLCode();
            this.canvasRenderingContext.fillText(text, xPosition, yPosition);
        }
        paintTextWithTextFormatAndMaxLength(xPosition, yPosition, text, textFormat, maxLength) {
            this.canvasRenderingContext.fillText(text, xPosition, yPosition);
        }
        popClipArea() {
            this.currentClipArea = undefined;
            this.canvasRenderingContext.closePath();
            this.canvasRenderingContext.restore();
        }
        pushClipArea(clipArea) {
            if (clipArea === null) {
                throw new Error('The given clipArea is null.');
            }
            if (clipArea === undefined) {
                throw new Error('The given clipArea is undefined.');
            }
            this.currentClipArea = clipArea;
            const fillStyle = this.canvasRenderingContext.fillStyle;
            this.canvasRenderingContext.save();
            this.canvasRenderingContext.fillStyle = fillStyle;
            this.canvasRenderingContext.rect(clipArea.getXPosition(), clipArea.getYPosition(), clipArea.getWidth(), clipArea.getHeight());
            this.canvasRenderingContext.clip();
            this.canvasRenderingContext.beginPath();
        }
        setColor(color) {
            this.canvasRenderingContext.fillStyle = color.getHTMLCode();
        }
        setOpacityPercentage(opacityPercentage) {
            if (opacityPercentage < 0.0) {
                throw new Error('The given opacityPercentage is negative.');
            }
            if (opacityPercentage > 1.0) {
                throw new Error('The given opacityPercentage is bigger than 1.0.');
            }
            this.canvasRenderingContext.globalAlpha = opacityPercentage;
        }
        createLinearGradient(xPosition, yPosition, width, height, colorGradient) {
            const linearGradient = this.createLinearGradientForDirectionInRectangle(xPosition, yPosition, width, height, colorGradient.getDirection());
            linearGradient.addColorStop(0, colorGradient.getColor1().getHTMLCode());
            linearGradient.addColorStop(1, colorGradient.getColor2().getHTMLCode());
            return linearGradient;
        }
        createLinearGradientForDirectionInRectangle(xPosition, yPosition, width, height, directionInRectangle) {
            switch (directionInRectangle) {
                case DirectionInRectangle_2.DirectionInRectangle.VERTICAL:
                    return this.canvasRenderingContext.createLinearGradient(xPosition, yPosition, xPosition, yPosition + height);
                case DirectionInRectangle_2.DirectionInRectangle.HORIZONTAL:
                    return this.canvasRenderingContext.createLinearGradient(xPosition, yPosition, xPosition + width, yPosition);
                case DirectionInRectangle_2.DirectionInRectangle.DIAGONAL_DOWN:
                    return this.canvasRenderingContext.createLinearGradient(xPosition, yPosition, xPosition + width, yPosition + height);
                case DirectionInRectangle_2.DirectionInRectangle.DIAGONAL_UP:
                    return this.canvasRenderingContext.createLinearGradient(xPosition, yPosition + height, xPosition + width, yPosition);
            }
        }
    }
    CanvasGUIGlobalPainter.DEFAULT_TEXT_FONT_TYPE = FontType_2.FontType.VERDANA;
    CanvasGUIGlobalPainter.DEFAULT_TEXT_SIZE = 10;
    CanvasGUIGlobalPainter.DEFAULT_TEXT_COLOR = Color_3.Color.BLACK;
    CanvasGUIGlobalPainter.DEFAULT_TEXT_FORMAT = new TextFormat_1.TextFormat(new Font_2.Font(CanvasGUIGlobalPainter.DEFAULT_TEXT_FONT_TYPE), CanvasGUIGlobalPainter.DEFAULT_TEXT_SIZE, CanvasGUIGlobalPainter.DEFAULT_TEXT_COLOR);
    exports.CanvasGUIGlobalPainter = CanvasGUIGlobalPainter;
});
define("SystemAPI/GUIAPI/PainterAPI/IPainter", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("System/GUI/CanvasGUI/CanvasGUIPainter", ["require", "exports", "System/GUI/CanvasGUI/CanvasGUIGlobalPainter", "Core/Math/CentralCalculator", "Core/Container/SingleContainer", "Core/GridUniversalAPI/TopLeftPositionedRectangle"], function (require, exports, CanvasGUIGlobalPainter_1, CentralCalculator_1, SingleContainer_1, TopLeftPositionedRectangle_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUIPainter {
        constructor(xPosition, yPosition, optionalClipAreaOnViewArea, globalPainter, parentPainterContainer) {
            this.DEFAULT_OPACITY = 1.0;
            this.opacity = this.DEFAULT_OPACITY;
            if (xPosition === null) {
                throw new Error('The given xPosition is null.');
            }
            if (xPosition === undefined) {
                throw new Error('The given xPosition is undefined.');
            }
            if (yPosition === null) {
                throw new Error('The given yPosition is null.');
            }
            if (yPosition === undefined) {
                throw new Error('The given yPosition is undefined.');
            }
            if (globalPainter === null) {
                throw new Error('The given globalPainter is null.');
            }
            if (globalPainter === undefined) {
                throw new Error('The given globalPainter is undefined.');
            }
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.globalPainter = globalPainter;
            this.parentPainter = parentPainterContainer.isEmpty() ? undefined : parentPainterContainer.getRefElement();
            if (optionalClipAreaOnViewArea.containsAny()) {
                this.clipAreaOnViewArea = optionalClipAreaOnViewArea.getRefElement();
            }
        }
        static createPainterFor(imageCache, canvasRenderingContext) {
            return new CanvasGUIPainter(0, 0, SingleContainer_1.SingleContainer.withoutElement(), new CanvasGUIGlobalPainter_1.CanvasGUIGlobalPainter(imageCache, canvasRenderingContext), SingleContainer_1.SingleContainer.EMPTY_CONTAINER);
        }
        createPainter() {
            return new CanvasGUIPainter(0, 0, this.getOptionalClipAreaOnViewArea(), this.globalPainter, SingleContainer_1.SingleContainer.withElement(this));
        }
        createPainterWithTranslation(xTranslation, yTranslation) {
            return new CanvasGUIPainter(xTranslation, yTranslation, this.getOptionalClipAreaOnViewArea(), this.globalPainter, SingleContainer_1.SingleContainer.withElement(this));
        }
        createPainterWithTranslationAndPaintArea(xTranslation, yTranslation, clipAreaWidth, clipAreaHeight) {
            if (!this.hasClipArea()) {
                return this.createPainterWithTranslationAndPaintAreaWhenDoesNotHaveClipArea(xTranslation, yTranslation, clipAreaWidth, clipAreaHeight);
            }
            return this.createPainterWithTranslationAndPaintAreaWhenHasClipArea(xTranslation, yTranslation, clipAreaWidth, clipAreaHeight);
        }
        descendsFromOtherPainter() {
            return (this.parentPainter !== undefined);
        }
        getEffectiveOpacity() {
            if (!this.descendsFromOtherPainter()) {
                return this.getOpacity();
            }
            return (this.parentPainter.getEffectiveOpacity() * this.getOpacity());
        }
        getOpacity() {
            return this.opacity;
        }
        getOptionalClipAreaOnViewArea() {
            return (this.hasClipArea() ? SingleContainer_1.SingleContainer.withElement(this.clipAreaOnViewArea) : SingleContainer_1.SingleContainer.withoutElement());
        }
        getXPositionOnViewArea() {
            if (this.parentPainter === undefined) {
                return this.xPosition;
            }
            return this.parentPainter.getXPositionOnViewArea() + this.xPosition;
        }
        getYPositionOnViewArea() {
            if (this.parentPainter === undefined) {
                return this.yPosition;
            }
            return this.parentPainter.getYPositionOnViewArea() + this.yPosition;
        }
        hasClipArea() {
            return (this.clipAreaOnViewArea !== undefined);
        }
        paintFilledRectangle(width, height) {
            this.paintFilledRectangleAtPosition(0, 0, this.getXPositionOnViewArea(), this.getYPositionOnViewArea());
        }
        paintFilledRectangleAtPosition(xPosition, yPosition, width, height) {
            this.pushClipArea();
            if (!this.hasColorGradient()) {
                this.globalPainter.paintFilledRectangle(this.getXPositionOnViewArea() + xPosition, this.getYPositionOnViewArea() + yPosition, width, height);
            }
            else {
                this.globalPainter.paintFilledRectangleWithColorGradient(this.getXPositionOnViewArea() + xPosition, this.getYPositionOnViewArea() + yPosition, width, height, this.colorGradient);
            }
            this.popClipArea();
        }
        paintImage(image) {
            this.pushClipArea();
            this.globalPainter.paintImage(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), image);
            this.popClipArea();
        }
        paintImageById(id) {
            this.pushClipArea();
            this.globalPainter.paintImageById(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), id);
            this.popClipArea();
        }
        paintImageByIdWithSize(id, width, height) {
            this.pushClipArea();
            this.globalPainter.paintImageByIdWithSize(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), id, width, height);
            this.popClipArea();
        }
        paintImageWidthSize(image, width, height) {
            this.pushClipArea();
            this.globalPainter.paintImageWithSize(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), image, width, height);
            this.popClipArea();
        }
        paintText(text) {
            this.pushClipArea();
            this.globalPainter.paintText(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), text);
            this.popClipArea();
        }
        paintTextWithTextFormat(text, textFormat) {
            this.pushClipArea();
            this.globalPainter.paintTextWithTextFormat(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), text, textFormat);
            this.popClipArea();
        }
        paintTextWithTextFormatAndMaxLength(text, textFormat, maxLength) {
            this.pushClipArea();
            this.globalPainter.paintTextWithTextFormatAndMaxLength(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), text, textFormat, maxLength);
            this.popClipArea();
        }
        setColor(color) {
            this.colorGradient = undefined;
            this.globalPainter.setColor(color);
        }
        setColorGradient(colorGradient) {
            this.colorGradient = colorGradient;
        }
        setOpacityPercentage(opacityPercentage) {
            if (opacityPercentage < 0.0) {
                throw new Error('The given opacityPercentage is negative.');
            }
            if (opacityPercentage > 1.0) {
                throw new Error('The given opacityPercentage is bigger than 1.0.');
            }
            this.opacity = opacityPercentage;
            this.applyOpacity();
        }
        translate(xTranslation, yTranslation) {
            this.pushClipArea();
            this.xPosition += xTranslation;
            this.yPosition += yTranslation;
            this.popClipArea();
        }
        applyOpacity() {
            this.globalPainter.setOpacityPercentage(this.getEffectiveOpacity());
        }
        createPainterWithTranslationAndPaintAreaWhenDoesNotHaveClipArea(xTranslation, yTranslation, clipAreaWidth, clipAreaHeight) {
            return new CanvasGUIPainter(xTranslation, yTranslation, SingleContainer_1.SingleContainer.withElement(new TopLeftPositionedRectangle_1.TopLeftPositionedRectangle(this.getXPositionOnViewArea() + xTranslation, this.getYPositionOnViewArea() + yTranslation, clipAreaWidth, clipAreaHeight)), this.globalPainter, SingleContainer_1.SingleContainer.withElement(this));
        }
        createPainterWithTranslationAndPaintAreaWhenHasClipArea(xTranslation, yTranslation, clipAreaWidth, clipAreaHeight) {
            const clipAreaOnViewAreaXPosition = CentralCalculator_1.CentralCalculator.getMax(this.clipAreaOnViewArea.getXPosition(), this.getXPositionOnViewArea() + xTranslation);
            const clipAreaOnViewAreaYPosition = CentralCalculator_1.CentralCalculator.getMax(this.clipAreaOnViewArea.getYPosition(), this.getYPositionOnViewArea() + yTranslation);
            const clipAreaOnViewAreaRightXPosition = CentralCalculator_1.CentralCalculator.getMin(this.clipAreaOnViewArea.getXPosition() + this.clipAreaOnViewArea.getWidth(), this.getXPositionOnViewArea() + xTranslation + clipAreaWidth);
            const clipAreaOnViewAreaBottomPosition = CentralCalculator_1.CentralCalculator.getMin(this.clipAreaOnViewArea.getYPosition() + this.clipAreaOnViewArea.getHeight(), this.getYPositionOnViewArea() + yTranslation + clipAreaHeight);
            const clipAreaOnViewAreaWidth = CentralCalculator_1.CentralCalculator.getMax(0, clipAreaOnViewAreaRightXPosition - clipAreaOnViewAreaXPosition);
            const clipAreaOnViewAreaHeight = CentralCalculator_1.CentralCalculator.getMax(0, clipAreaOnViewAreaBottomPosition - clipAreaOnViewAreaYPosition);
            return new CanvasGUIPainter(xTranslation, yTranslation, SingleContainer_1.SingleContainer.withElement(new TopLeftPositionedRectangle_1.TopLeftPositionedRectangle(clipAreaOnViewAreaXPosition, clipAreaOnViewAreaYPosition, clipAreaOnViewAreaWidth, clipAreaOnViewAreaHeight)), this.globalPainter, SingleContainer_1.SingleContainer.withElement(this));
        }
        hasColorGradient() {
            return (this.colorGradient !== undefined);
        }
        popClipArea() {
            if (this.hasClipArea() && this.globalPainter.hasGivenClipArea(this.clipAreaOnViewArea)) {
                this.globalPainter.popClipArea();
            }
        }
        pushClipArea() {
            if (this.hasClipArea() && !this.globalPainter.hasGivenClipArea(this.clipAreaOnViewArea)) {
                this.globalPainter.pushClipArea(this.clipAreaOnViewArea);
            }
        }
    }
    exports.CanvasGUIPainter = CanvasGUIPainter;
});
define("System/GUI/CursorIcon/CursorIconType", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var CursorIconType;
    (function (CursorIconType) {
        CursorIconType[CursorIconType["Arrow"] = 0] = "Arrow";
        CursorIconType[CursorIconType["Cross"] = 1] = "Cross";
        CursorIconType[CursorIconType["Edit"] = 2] = "Edit";
        CursorIconType[CursorIconType["Hand"] = 3] = "Hand";
        CursorIconType[CursorIconType["Move"] = 4] = "Move";
        CursorIconType[CursorIconType["Wait"] = 5] = "Wait";
    })(CursorIconType = exports.CursorIconType || (exports.CursorIconType = {}));
});
define("System/GUI/CursorIcon/CursorIcon", ["require", "exports", "System/GUI/CursorIcon/CursorIconType"], function (require, exports, CursorIconType_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CursorIcon {
        static fromSpecification(specification) {
            return new CursorIcon(CursorIconType_1.CursorIconType[specification.getOneAttributeHeader()]);
        }
        constructor(cursorIconType) {
            if (cursorIconType === null) {
                console.log('The given cursorIconType is null.');
            }
            if (cursorIconType === undefined) {
                console.log('The given cursorIconType is undefined.');
            }
            this.cursorIconType = cursorIconType;
        }
        toEnum() {
            return this.cursorIconType;
        }
        toHTMLCode() {
            switch (this.toEnum()) {
                case CursorIconType_1.CursorIconType.Arrow:
                    return 'default';
                case CursorIconType_1.CursorIconType.Cross:
                    return 'crosshair';
                case CursorIconType_1.CursorIconType.Edit:
                    return 'text';
                case CursorIconType_1.CursorIconType.Hand:
                    return 'pointer';
                case CursorIconType_1.CursorIconType.Move:
                    return 'move';
                case CursorIconType_1.CursorIconType.Wait:
                    return 'wait';
            }
        }
    }
    exports.CursorIcon = CursorIcon;
});
define("System/GUI/Input/KeyMapper", ["require", "exports", "System/GUI/Input/Key"], function (require, exports, Key_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class KeyMapper {
        static createKeyFromKeyboardEvent(keyboardEvent) {
            switch (keyboardEvent.key) {
                case 'A':
                case 'a':
                    return Key_2.Key.A;
                case 'B':
                case 'b':
                    return Key_2.Key.B;
                case 'C':
                case 'c':
                    return Key_2.Key.C;
                case 'D':
                case 'd':
                    return Key_2.Key.D;
                case 'E':
                case 'e':
                    return Key_2.Key.E;
                case 'F':
                case 'f':
                    return Key_2.Key.F;
                case 'G':
                case 'g':
                    return Key_2.Key.G;
                case 'H':
                case 'h':
                    return Key_2.Key.H;
                case 'I':
                case 'i':
                    return Key_2.Key.I;
                case 'J':
                case 'j':
                    return Key_2.Key.J;
                case 'K':
                case 'k':
                    return Key_2.Key.K;
                case 'L':
                case 'l':
                    return Key_2.Key.L;
                case 'M':
                case 'm':
                    return Key_2.Key.M;
                case 'N':
                case 'n':
                    return Key_2.Key.N;
                case 'O':
                case 'o':
                    return Key_2.Key.O;
                case 'P':
                case 'p':
                    return Key_2.Key.P;
                case 'Q':
                case 'q':
                    return Key_2.Key.Q;
                case 'R':
                case 'r':
                    return Key_2.Key.R;
                case 'S':
                case 's':
                    return Key_2.Key.S;
                case 'T':
                case 't':
                    return Key_2.Key.T;
                case 'U':
                case 'u':
                    return Key_2.Key.U;
                case 'V':
                case 'v':
                    return Key_2.Key.V;
                case 'W':
                case 'w':
                    return Key_2.Key.W;
                case 'X':
                case 'x':
                    return Key_2.Key.X;
                case 'Y':
                case 'y':
                    return Key_2.Key.Y;
                case 'Z':
                case 'z':
                    return Key_2.Key.Z;
                case 'Ä':
                case 'ä':
                    return Key_2.Key.AE;
                case 'Ö':
                case 'ö':
                    return Key_2.Key.OE;
                case 'Ü':
                case 'ü':
                    return Key_2.Key.UE;
                case '0':
                    return Key_2.Key.NUMBERPAD_0;
                case '1':
                    return Key_2.Key.NUMBERPAD_1;
                case '2':
                    return Key_2.Key.NUMBERPAD_2;
                case '3':
                    return Key_2.Key.NUMBERPAD_3;
                case '4':
                    return Key_2.Key.NUMBERPAD_4;
                case '5':
                    return Key_2.Key.NUMBERPAD_5;
                case '6':
                    return Key_2.Key.NUMBERPAD_6;
                case '7':
                    return Key_2.Key.NUMBERPAD_7;
                case '8':
                    return Key_2.Key.NUMBERPAD_8;
                case '9':
                    return Key_2.Key.NUMBERPAD_9;
                case '$':
                    return Key_2.Key.DOLLAR_SYMBOL;
                case '-':
                    return Key_2.Key.HYPHEN;
                case 'F1':
                    return Key_2.Key.F1;
                case 'F2':
                    return Key_2.Key.F2;
                case 'F3':
                    return Key_2.Key.F3;
                case 'F4':
                    return Key_2.Key.F4;
                case 'F5':
                    return Key_2.Key.F5;
                case 'F6':
                    return Key_2.Key.F6;
                case 'F7':
                    return Key_2.Key.F7;
                case 'F8':
                    return Key_2.Key.F8;
                case 'F9':
                    return Key_2.Key.F9;
                case 'F10':
                    return Key_2.Key.F10;
                case 'F11':
                    return Key_2.Key.F11;
                case 'F12':
                    return Key_2.Key.F12;
                case 'ArrowLeft':
                    return Key_2.Key.ARROW_LEFT;
                case 'ArrowRight':
                    return Key_2.Key.ARROW_RIGHT;
                case 'ArrowUp':
                    return Key_2.Key.ARROW_UP;
                case 'ArrowDown':
                    return Key_2.Key.ARROW_DOWN;
                case 'Alt':
                    return Key_2.Key.ALTERNATIVE;
                case 'Backspace':
                    return Key_2.Key.BACKSPACE;
                case 'CapsLock':
                    return Key_2.Key.CAPS_LOCK;
                case 'Control':
                    return Key_2.Key.CONTROL;
                case 'Delete':
                    return Key_2.Key.DELETE;
                case 'Escape':
                    return Key_2.Key.ESCAPE;
                case 'Enter':
                    return Key_2.Key.ENTER;
                case 'Shift':
                    return Key_2.Key.SHIFT;
                case ' ':
                    return Key_2.Key.SPACE;
                case 'Tab':
                    return Key_2.Key.TABULATOR;
                default:
                    throw new Error('For the KeyboardEvent \'' + keyboardEvent.key + '\' there is not defined a key.');
            }
        }
    }
    exports.KeyMapper = KeyMapper;
});
define("System/GUI/CanvasGUI/PaintProcess", ["require", "exports", "Core/Container/LinkedList"], function (require, exports, LinkedList_11) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class PaintProcess {
        constructor(initialPainter, paintCommands) {
            this.painters = new LinkedList_11.LinkedList();
            this.addPainter(initialPainter);
            paintCommands.forEach(pp => pp(this));
        }
        addPainter(painter) {
            this.painters.addAtEnd(painter);
        }
        getRefPainterByIndex(index) {
            return this.painters.getRefAt(index);
        }
    }
    exports.PaintProcess = PaintProcess;
});
define("System/GUI/ProcessProperty/RotationDirectionMapper", ["require", "exports", "SystemAPI/GUIAPI/ProcessProperty/RotationDirection"], function (require, exports, RotationDirection_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class RotationDirectionMapper {
        static createRotationDirectionFromWheelEvent(wheelEvent) {
            return (wheelEvent.deltaY > 0 ? RotationDirection_2.RotationDirection.FORWARD : RotationDirection_2.RotationDirection.BACKWARD);
        }
        constructor() { }
    }
    exports.RotationDirectionMapper = RotationDirectionMapper;
});
define("System/GUI/CanvasGUI/CanvasGUI", ["require", "exports", "Core/Container/Caching/CachingContainer", "System/GUI/CanvasGUI/CanvasGUICommandProtocol", "System/GUI/CanvasGUI/CanvasGUIObjectProtocol", "System/GUI/CanvasGUI/CanvasGUIPainter", "System/GUI/Color/Color", "System/GUI/Color/ColorGradient", "System/GUI/Input/KeyMapper", "Core/Container/LinkedList", "System/GUI/CanvasGUI/PaintProcess", "Core/Container/Pair/Pair", "System/GUI/ProcessProperty/RotationDirectionMapper", "System/GUI/TextFormat/TextFormat", "Core/Container/SingleContainer"], function (require, exports, CachingContainer_1, CanvasGUICommandProtocol_1, CanvasGUIObjectProtocol_1, CanvasGUIPainter_1, Color_4, ColorGradient_1, KeyMapper_1, LinkedList_12, PaintProcess_1, Pair_2, RotationDirectionMapper_1, TextFormat_2, SingleContainer_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUI {
        constructor(window, inputTaker) {
            this.imageCache = new CachingContainer_1.CachingContainer();
            if (window === null) {
                throw new Error('The given window is null.');
            }
            if (window === undefined) {
                throw new Error('The given window is undefined.');
            }
            if (inputTaker === null) {
                throw new Error('The given inputTaker is null.');
            }
            if (inputTaker === undefined) {
                throw new Error('The given inputTaker is undefined.');
            }
            this.inputTaker = inputTaker;
            this.paintCommands = new LinkedList_12.LinkedList();
            this.window = window;
            this.canvas = window.document.createElement('canvas');
            this.window.document.body.appendChild(this.canvas);
            this.window.document.body.style.margin = '0px';
            this.canvasRenderingContext2D = this.canvas.getContext('2d');
            this.updateCanvasSize();
            this.connectInputMethods();
            this.reset();
        }
        getCursorXPositionOnViewArea() {
            return this.cursorXPositionOnViewArea;
        }
        getCursorYPositionOnViewArea() {
            return this.cursorYPositionOnViewArea;
        }
        getOptionalFile(fileTaker) {
            const uploaderDiv = this.window.document.createElement('div');
            this.window.document.body.appendChild(uploaderDiv);
            const uploader = this.window.document.createElement('input');
            uploader.type = 'file';
            uploader.innerText = 'Upload';
            uploader.onchange = () => {
                if (uploader.files.length > 0) {
                    const file = uploader.files[0];
                    const fileReader = new FileReader();
                    fileReader.readAsDataURL(file);
                    fileReader.onloadend = () => {
                        if (fileReader.readyState === FileReader.DONE) {
                            const file = fileReader.result.toString();
                            fileTaker(SingleContainer_2.SingleContainer.withElement(file));
                        }
                    };
                    this.window.document.body.removeChild(uploaderDiv);
                }
            };
            uploaderDiv.appendChild(uploader);
            const cancelButton = this.window.document.createElement('button');
            cancelButton.type = 'button';
            cancelButton.innerText = 'Cancel';
            cancelButton.onclick = () => {
                fileTaker(SingleContainer_2.SingleContainer.withoutElement());
                this.window.document.body.removeChild(uploaderDiv);
            };
            uploaderDiv.appendChild(cancelButton);
        }
        getTitle() {
            return this.title;
        }
        getViewAreaHeight() {
            return this.viewAreaHeight;
        }
        getViewAreaSize() {
            return new Pair_2.Pair(this.getViewAreaWidth(), this.getViewAreaHeight());
        }
        getViewAreaWidth() {
            return this.viewAreaWidth;
        }
        noteKeyPress(key) {
            this.inputTaker.noteKeyPress(key);
        }
        noteKeyRelease(key) {
            this.inputTaker.noteKeyRelease(key);
        }
        noteLeftMouseButtonClick() {
            console.log('The current CanvasGUI notes a left mouse button click.');
            this.inputTaker.noteLeftMouseButtonClick();
        }
        noteLeftMouseButtonPress() {
            console.log('The current CanvasGUI notes a left mouse button press.');
            this.inputTaker.noteLeftMouseButtonPress();
        }
        noteLeftMouseButtonRelease() {
            console.log('The current CanvasGUI notes a left mouse button release.');
            this.inputTaker.noteLeftMouseButtonRelease();
        }
        noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            this.setCursorPosition(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
            this.inputTaker.noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
        }
        noteMouseWheelClick() {
            console.log('The current CanvasGUI notes a mouse wheel click.');
            this.inputTaker.noteMouseWheelClick();
        }
        noteMouseWheelPress() {
            console.log('The current CanvasGUI notes a mouse wheel press.');
            this.inputTaker.noteMouseWheelPress();
        }
        noteMouseWheelRelease() {
            console.log('The current CanvasGUI notes a mouse wheel release.');
            this.inputTaker.noteMouseWheelRelease();
        }
        noteMouseWheelRotationStep(rotationDirection) {
            console.log('The current CanvasGUI notes a mouse wheel rotation step.');
            this.inputTaker.noteMouseWheelRotationStep(rotationDirection);
        }
        noteResize() {
            console.log('The current CanvasGUI notes a resize.');
            this.updateCanvasSize();
            this.inputTaker.noteResize(this.getViewAreaWidth(), this.getViewAreaHeight());
        }
        noteRightMouseButtonClick() {
            console.log('The current CanvasGUI notes a right mouse button click.');
            this.inputTaker.noteRightMouseButtonClick();
        }
        noteRightMouseButtonPress() {
            console.log('The current CanvasGUI notes a right mouse button press.');
            this.inputTaker.noteRightMouseButtonPress;
        }
        noteRightMouseButtonRelease() {
            console.log('The current CanvasGUI notes a right mouse button release.');
            this.inputTaker.noteRightMouseButtonRelease();
        }
        openNewTabWithURL(pURL) {
            if (!pURL.startsWith('http://')) {
                pURL = 'http://' + pURL;
            }
            console.log('The current CanvasGUI opens a new tab with the URL \'' + pURL + '\'');
            this.window.open(pURL, '_blank');
        }
        redirectTo(pURL) {
            if (!pURL.startsWith('http://')) {
                pURL = 'http://' + pURL;
            }
            console.log('The current CanvasGUI redirects to \'' + pURL + '\'');
            this.window.open(pURL, '_self');
        }
        refresh() {
            console.log('The current CanvasGUI refreshes.');
            this.canvasRenderingContext2D.clearRect(0, 0, this.getViewAreaWidth(), this.getViewAreaHeight());
            const painter = CanvasGUIPainter_1.CanvasGUIPainter.createPainterFor(this.imageCache, this.canvasRenderingContext2D);
            this.paintBackground(painter);
            new PaintProcess_1.PaintProcess(painter, this.paintCommands);
        }
        registerImageAtId(id, image) {
            if (!this.imageCache.containsWithId(id)) {
                console.log('The current CanvasGUI registers the image with the id \'' + id + '\'');
                this.imageCache.registerAtId(id, image);
            }
        }
        reset() {
            this.setTitle(CanvasGUI.DEFAULT_TITLE);
            this.cursorXPositionOnViewArea = 0;
            this.cursorYPositionOnViewArea = 0;
            this.paintCommands.clear();
            this.refresh();
        }
        setCursorIcon(cursorIcon) {
            this.canvas.style.cursor = cursorIcon.toHTMLCode();
        }
        setIcon(icon) {
            if (icon === null) {
                throw new Error('The given icon is null.');
            }
            if (icon === undefined) {
                throw new Error('The given icon is undefined.');
            }
            this.icon = icon;
            const favicon = document.getElementById('icon');
            favicon.href = icon.toCanvas().toDataURL('image/png');
        }
        setPaintCommands(paintCommands) {
            this.paintCommands.refill(paintCommands);
        }
        setTextualPaintCommands(textualPaintCommands) {
            this.setPaintCommands(textualPaintCommands.to(tpc => this.createPaintCommand(tpc)));
        }
        setTitle(title) {
            if (title === null) {
                throw new Error('The given title is null.');
            }
            if (title === undefined) {
                throw new Error('The given title is undefined.');
            }
            this.title = title;
            this.window.document.title = this.title;
        }
        webBrowserIsFirefox() {
            return navigator.userAgent.includes('Firefox');
        }
        connectInputMethods() {
            this.window.onresize = () => this.noteResize();
            this.connectKeyInputMethods();
            this.connectMouseInputMethods();
        }
        connectKeyInputMethods() {
            this.window.onkeydown = (ke) => this.noteKeyPress(KeyMapper_1.KeyMapper.createKeyFromKeyboardEvent(ke));
            this.window.onkeyup = (ke) => this.noteKeyRelease(KeyMapper_1.KeyMapper.createKeyFromKeyboardEvent(ke));
        }
        connectMouseInputMethods() {
            this.canvas.onmousemove = (me) => this.noteMouseMove(me.offsetX, me.offsetY);
            this.canvas.onmousedown = (me) => this.noteMouseButtonPress(me);
            this.canvas.onmouseup = (me) => this.noteMouseButtonRelease(me);
            this.canvas.onclick = (me) => this.noteMouseButtonClick(me);
            this.canvas.onwheel = (we) => this.noteMouseWheelInput(we);
        }
        createCreatePainterCommand(painterIndex, textualCreatePainterCommand) {
            const attributes = textualCreatePainterCommand.getAttributes();
            const xTranslation = attributes.getRefAt(1).toNumber();
            const yTranslation = attributes.getRefAt(2).toNumber();
            switch (attributes.getSize()) {
                case 2:
                    return pp => {
                        const painter = pp.getRefPainterByIndex(painterIndex).createPainterWithTranslation(xTranslation, yTranslation);
                        pp.addPainter(painter);
                    };
                case 4:
                    const paintAreaWidth = attributes.getRefAt(3).toNumber();
                    const paintAreaHeight = attributes.getRefAt(4).toNumber();
                    return pp => {
                        const painter = pp.getRefPainterByIndex(painterIndex).createPainterWithTranslationAndPaintArea(xTranslation, yTranslation, paintAreaWidth, paintAreaHeight);
                        pp.addPainter(painter);
                    };
                default:
                    throw new Error('The given textualCreatePainterCommand is not valid.');
            }
        }
        createPaintCommand(textualPaintCommand) {
            const painterIndex = textualPaintCommand.getOneAttributeAsNumber();
            return this.createPaintCommandUsingPainterIndex(painterIndex, textualPaintCommand.getNextNode());
        }
        createPaintCommandUsingPainterIndex(painterIndex, textualPaintCommand) {
            switch (textualPaintCommand.getHeader()) {
                case CanvasGUIObjectProtocol_1.CanvasGUIObjectProtocol.CREATE_PAINTER:
                    return this.createCreatePainterCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.PAINT_FILLED_RECTANGLE:
                    return this.createPaintFilledRectangleCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID:
                    return this.createPaintImageByIdCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.PAINT_TEXT:
                    return this.createPaintTextCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.SET_COLOR:
                    return this.createSetColorCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.SET_COLOR_GRADIENT:
                    return this.createSetColorGradientCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.SET_OPACITY_PERCENTAGE:
                    return this.createSetOpacityPercentageCommand(painterIndex, textualPaintCommand);
                case CanvasGUICommandProtocol_1.CanvasGUICommandProtocol.TRANSLATE:
                    return this.createTranslateCommand(painterIndex, textualPaintCommand);
                default:
                    throw new Error('The given textualPaintCommand is not valid.');
            }
        }
        createPaintFilledRectangleCommand(painterIndex, textualPaintFilledRectangleCommand) {
            const attributes = textualPaintFilledRectangleCommand.getAttributes();
            switch (attributes.getSize()) {
                case 2:
                    const width = attributes.getRefAt(1).toNumber();
                    const height = attributes.getRefAt(2).toNumber();
                    return pp => pp.getRefPainterByIndex(painterIndex).paintFilledRectangle(width, height);
                case 4:
                    const xPosition = attributes.getRefAt(1).toNumber();
                    const yPosition = attributes.getRefAt(2).toNumber();
                    const width2 = attributes.getRefAt(3).toNumber();
                    const height2 = attributes.getRefAt(4).toNumber();
                    return pp => pp.getRefPainterByIndex(painterIndex).paintFilledRectangleAtPosition(xPosition, yPosition, width2, height2);
                default:
                    throw new Error('The given textualPaintFilledRectangleCommand is not valid.');
            }
        }
        createPaintImageByIdCommand(painterIndex, textualPaintImageByIdCommand) {
            const imageId = textualPaintImageByIdCommand.getAttributeAt(1).getHeader();
            switch (textualPaintImageByIdCommand.getAttributeCount()) {
                case 1:
                    return pp => pp.getRefPainterByIndex(painterIndex).paintImageById(imageId);
                case 3:
                    const imageWidth = textualPaintImageByIdCommand.getAttributeAt(2).toNumber();
                    const imageHeight = textualPaintImageByIdCommand.getAttributeAt(3).toNumber();
                    return pp => pp.getRefPainterByIndex(painterIndex).paintImageByIdWithSize(imageId, imageWidth, imageHeight);
                default:
                    throw new Error('The given textualPaintImageByIdCommand is not valid.');
            }
        }
        createPaintTextCommand(painterIndex, textualPaintTextCommand) {
            const text = textualPaintTextCommand.getAttributeAt(1).getHeaderOrEmptyString();
            switch (textualPaintTextCommand.getAttributeCount()) {
                case 1:
                    return pp => pp.getRefPainterByIndex(painterIndex).paintText(text);
                case 2:
                    const textFormat = TextFormat_2.TextFormat.fromSpecification(textualPaintTextCommand.getAttributeAtAsNode(2));
                    return pp => pp.getRefPainterByIndex(painterIndex).paintTextWithTextFormat(text, textFormat);
                case 3:
                    const textFormat2 = TextFormat_2.TextFormat.fromSpecification(textualPaintTextCommand.getAttributeAtAsNode(2));
                    const maxLength = textualPaintTextCommand.getAttributeAt(3).toNumber();
                    return pp => pp.getRefPainterByIndex(painterIndex).paintTextWithTextFormatAndMaxLength(text, textFormat2, maxLength);
                default:
                    throw new Error('The given textualPaintTextCommand is not valid.');
            }
        }
        createSetColorCommand(painterIndex, textualSetColorCommand) {
            const color = Color_4.Color.fromSpecification(textualSetColorCommand.getOneAttributeAsNode());
            return pp => pp.getRefPainterByIndex(painterIndex).setColor(color);
        }
        createSetColorGradientCommand(painterIndex, textualSetColorGradientCommand) {
            const colorGradient = ColorGradient_1.ColorGradient.fromSpecification(textualSetColorGradientCommand.getOneAttributeAsNode());
            return pp => pp.getRefPainterByIndex(painterIndex).setColorGradient(colorGradient);
        }
        createSetOpacityPercentageCommand(painterIndex, textualSetOpacityPercentageCommand) {
            const opacityPercentage = textualSetOpacityPercentageCommand.getOneAttributeAsNumber();
            return pp => pp.getRefPainterByIndex(painterIndex).setOpacityPercentage(opacityPercentage);
        }
        createTranslateCommand(painterIndex, translateCommand) {
            const attributes = translateCommand.getAttributes();
            const xTranslation = attributes.getRefAt(1).toNumber();
            const yTranslation = attributes.getRefAt(2).toNumber();
            return pp => pp.getRefPainterByIndex(painterIndex).translate(xTranslation, yTranslation);
        }
        noteMouseButtonClick(mouseEvent) {
            this.setCursorPosition(mouseEvent.offsetX, mouseEvent.offsetY);
            switch (mouseEvent.button) {
                case 0:
                    this.noteLeftMouseButtonClick();
                    break;
                case 1:
                    this.noteMouseWheelClick();
                    break;
                case 2:
                    this.noteRightMouseButtonClick();
                    break;
                default:
                    throw new Error('The given mouseEvent is not valid.');
            }
        }
        noteMouseButtonPress(mouseEvent) {
            this.setCursorPosition(mouseEvent.offsetX, mouseEvent.offsetY);
            switch (mouseEvent.button) {
                case 0:
                    this.noteLeftMouseButtonPress();
                    break;
                case 1:
                    this.noteMouseWheelPress();
                    break;
                case 2:
                    this.noteRightMouseButtonPress();
                    break;
                default:
                    throw new Error('The given mouseEvent is not valid.');
            }
        }
        noteMouseButtonRelease(mouseEvent) {
            this.setCursorPosition(mouseEvent.offsetX, mouseEvent.offsetY);
            switch (mouseEvent.button) {
                case 0:
                    this.noteLeftMouseButtonRelease();
                    break;
                case 1:
                    this.noteMouseWheelRelease();
                    break;
                case 2:
                    this.noteRightMouseButtonRelease();
                    break;
                default:
                    throw new Error('The given mouseEvent is not valid.');
            }
        }
        noteMouseWheelInput(wheelEvent) {
            this.noteMouseWheelRotationStep(RotationDirectionMapper_1.RotationDirectionMapper.createRotationDirectionFromWheelEvent(wheelEvent));
        }
        paintBackground(painter) {
            painter.setColor(CanvasGUI.BACKGROUND_COLOR);
            painter.paintFilledRectangle(this.getViewAreaWidth(), this.getViewAreaHeight());
        }
        setCursorPosition(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            this.cursorXPositionOnViewArea = cursorXPositionOnViewArea;
            this.cursorYPositionOnViewArea = cursorYPositionOnViewArea;
        }
        updateCanvasSize() {
            this.viewAreaWidth = this.window.innerWidth;
            this.viewAreaHeight = this.window.innerHeight - 5;
            this.canvas.width = CanvasGUI.MONITOR_PIXELS_PER_MODEL_PIXEL * this.getViewAreaWidth();
            this.canvas.height = CanvasGUI.MONITOR_PIXELS_PER_MODEL_PIXEL * this.getViewAreaHeight();
            this.canvas.style.width = this.getViewAreaWidth() + 'px';
            this.canvas.style.height = this.getViewAreaHeight() + 'px';
            this.canvasRenderingContext2D.scale(CanvasGUI.MONITOR_PIXELS_PER_MODEL_PIXEL, CanvasGUI.MONITOR_PIXELS_PER_MODEL_PIXEL);
        }
    }
    CanvasGUI.DEFAULT_TITLE = 'GUI';
    CanvasGUI.BACKGROUND_COLOR = Color_4.Color.WHITE;
    CanvasGUI.MONITOR_PIXELS_PER_MODEL_PIXEL = 2;
    CanvasGUI.MODEL_PIXELS_PER_MONITOR_PIXEL = 1 / CanvasGUI.MONITOR_PIXELS_PER_MODEL_PIXEL;
    exports.CanvasGUI = CanvasGUI;
});
define("System/GUI/Graphic/CanvasImage", ["require", "exports", "Core/Constant/PascalCaseNameCatalogue"], function (require, exports, PascalCaseNameCatalogue_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasImage {
        static fromSpecification(specification, refresh) {
            const width = specification.getRefFirstAttributeWithHeader(PascalCaseNameCatalogue_3.PascalCaseNameCatalogue.WIDTH).getOneAttributeAsNumber();
            const height = specification.getRefFirstAttributeWithHeader(PascalCaseNameCatalogue_3.PascalCaseNameCatalogue.HEIGHT).getOneAttributeAsNumber();
            const lJPGString = specification.getRefFirstAttributeWithHeader('JPGString').getOneAttributeHeader();
            const image = document.createElement('img');
            image.width = width;
            image.height = height;
            const canvas = document.createElement('canvas');
            canvas.width = width;
            canvas.height = height;
            image.onload = function () {
                canvas.getContext('2d').drawImage(image, 0, 0, width, height);
                refresh();
            };
            image.src = 'data:image/jpeg;base64,' + lJPGString;
            return new CanvasImage(width, height, canvas);
        }
        constructor(width, height, canvas) {
            if (width < 1) {
                throw new Error('The given width is not positive.');
            }
            if (height < 1) {
                throw new Error('The given height is not positive.');
            }
            if (canvas === null) {
                throw new Error('The given canvas is null.');
            }
            if (canvas === undefined) {
                throw new Error('The given canvas is undefined.');
            }
            this.width = width;
            this.height = height;
            this.canvas = canvas;
        }
        getHeight() {
            return this.height;
        }
        getWidth() {
            return this.width;
        }
        toCanvas() {
            return this.canvas;
        }
    }
    exports.CanvasImage = CanvasImage;
});
define("System/GUI/Graphic/Image", ["require", "exports", "System/GUI/Color/Color", "System/Element/Element", "Core/Container/Matrix/Matrix", "Core/Document/Node/Node", "Core/Constant/PascalCaseNameCatalogue"], function (require, exports, Color_5, Element_2, Matrix_1, Node_7, PascalCaseNameCatalogue_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Image extends Element_2.Element {
        constructor(pixels) {
            super();
            this.pixels = pixels;
        }
        static fromSpecification(specification) {
            const pixels = new Matrix_1.Matrix();
            const width = specification.getRefFirstAttributeWithHeader(PascalCaseNameCatalogue_4.PascalCaseNameCatalogue.WIDTH).getOneAttributeAsNumber();
            var row = new Array();
            var i = 1;
            for (const a of specification.getRefFirstAttributeWithHeader(Image.PIXEL_ARRAY_HEADER).getRefAttributes()) {
                row.push(Color_5.Color.fromSpecification(Node_7.Node.withAttribute(a)));
                i++;
                if (i > width) {
                    pixels.addRow(row);
                    row = new Array();
                    i = 1;
                }
            }
            return new Image(pixels);
        }
        getAttributes() {
            throw new Error("Method not implemented.");
        }
        getHeight() {
            return this.pixels.getRowCount();
        }
        getPixelAtIndex(index) {
            return this.pixels.getRefAt(index);
        }
        getPixelAtPosition(xPosition, yPosition) {
            return this.pixels.getRefAtRowAndColumn(xPosition, yPosition);
        }
        getSizeInPixel() {
            return (this.getWidth() * this.getHeight());
        }
        getType() {
            return Image.TYPE_HEADER;
        }
        getWidth() {
            return this.pixels.getColumnCount();
        }
        toCanvas() {
            this.generateCanvasIfNeeded();
            return this.canvas;
        }
        generateCanvasIfNeeded() {
            if (this.generatingCanvasIsNeeded()) {
                this.generateCanvasWhenNeeded();
            }
        }
        generateCanvasWhenNeeded() {
            const width = this.getWidth();
            const height = this.getHeight();
            this.canvas = document.createElement('canvas');
            this.canvas.width = width;
            this.canvas.height = height;
            const context = this.canvas.getContext('2d');
            for (var rowIndex = 1; rowIndex <= height; rowIndex++) {
                for (var columnIndex = 1; columnIndex <= width; columnIndex++) {
                    const pixel = this.getPixelAtPosition(rowIndex, columnIndex);
                    context.fillStyle = pixel.getHTMLCode();
                    context.fillRect(columnIndex - 1, rowIndex - 1, 1, 1);
                }
            }
        }
        generatingCanvasIsNeeded() {
            return (this.canvas === undefined);
        }
    }
    Image.TYPE_HEADER = 'Image';
    Image.PIXEL_ARRAY_HEADER = 'PixelArray';
    exports.Image = Image;
});
define("System/Application/GUIApplication/GUIHandler", ["require", "exports", "System/GUI/CanvasGUI/CanvasGUI", "System/GUI/Graphic/CanvasImage", "System/GUI/CursorIcon/CursorIcon", "System/Application/GUIApplication/FrontCanvasGUIClientCommandProtocol", "System/Application/GUIApplication/FrontCanvasGUIClientObjectProtocol", "System/GUI/Graphic/Image"], function (require, exports, CanvasGUI_1, CanvasImage_1, CursorIcon_1, FrontCanvasGUIClientCommandProtocol_1, FrontCanvasGUIClientObjectProtocol_1, Image_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class GUIHandler {
        constructor(window, inputTaker) {
            this.mGUI = new CanvasGUI_1.CanvasGUI(window, inputTaker);
        }
        canRunCommand(command) {
            return (command.hasHeader() && this.canRunCommandOfType(command.getHeader()));
        }
        canRunCommandOfType(type) {
            switch (type) {
                case FrontCanvasGUIClientObjectProtocol_1.FrontCanvasGUIClientObjectProtocol.GUI:
                    return true;
                default:
                    return false;
            }
        }
        getCursorXPositionOnViewArea() {
            return this.mGUI.getCursorXPositionOnViewArea();
        }
        getCursorYPositionOnViewArea() {
            return this.mGUI.getCursorYPositionOnViewArea();
        }
        getOptionalFile(optionalFileTaker) {
            this.mGUI.getOptionalFile(optionalFileTaker);
        }
        getViewAreaSize() {
            return this.mGUI.getViewAreaSize();
        }
        openNewTabWithURL(pURL) {
            this.mGUI.openNewTabWithURL(pURL);
        }
        redirectTo(pURL) {
            this.mGUI.redirectTo(pURL);
        }
        registerImageAtId(id, image) {
            this.mGUI.registerImageAtId(id, image);
        }
        runGUICommand(pGUICommand) {
            switch (pGUICommand.getHeader()) {
                case FrontCanvasGUIClientCommandProtocol_1.FrontCanvasGUIClientCommandProtocol.REGISTER_IMAGE:
                    const id = pGUICommand.getAttributeAt(1).getHeader();
                    const image = CanvasImage_1.CanvasImage.fromSpecification(pGUICommand.getAttributeAt(2).toNode(), () => { return this.mGUI.refresh(); });
                    this.registerImageAtId(id, image);
                    break;
                case FrontCanvasGUIClientCommandProtocol_1.FrontCanvasGUIClientCommandProtocol.SET_TITLE:
                    this.mGUI.setTitle(pGUICommand.getOneAttributeAsString());
                    this.mGUI.refresh();
                    break;
                case FrontCanvasGUIClientCommandProtocol_1.FrontCanvasGUIClientCommandProtocol.SET_ICON:
                    this.mGUI.setIcon(Image_1.Image.fromSpecification(pGUICommand.getOneAttributeAsNode()));
                    this.mGUI.refresh();
                    break;
                case FrontCanvasGUIClientCommandProtocol_1.FrontCanvasGUIClientCommandProtocol.SET_CURSOR_ICON:
                    this.mGUI.setCursorIcon(CursorIcon_1.CursorIcon.fromSpecification(pGUICommand.getOneAttributeAsNode()));
                    break;
                case FrontCanvasGUIClientCommandProtocol_1.FrontCanvasGUIClientCommandProtocol.SET_PAINT_COMMANDS:
                    this.setPaintCommands(pGUICommand.getAttributes());
                    this.mGUI.refresh();
                    break;
                default:
                    throw new Error('The given pGUICommand is not valid: ' + pGUICommand);
            }
        }
        setPaintCommands(paintCommands) {
            this.mGUI.setTextualPaintCommands(paintCommands);
        }
    }
    exports.GUIHandler = GUIHandler;
});
define("System/GUI/Input/PerformanceFilterInputTaker", ["require", "exports", "System/GUI/Input/MouseInput", "System/GUI/Input/MouseInputType"], function (require, exports, MouseInput_2, MouseInputType_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class PerformanceFilterInputTaker {
        constructor(targetInputTaker) {
            if (targetInputTaker === null) {
                throw new Error('The given targetInputTaker is null.');
            }
            if (targetInputTaker === undefined) {
                throw new Error('The given targetInputTaker is undefined.');
            }
            this.targetInputTaker = targetInputTaker;
            this.latestMouseMoveTime = Date.now();
            const lThis = this;
            window.setInterval(function () { lThis.runLatestSkippedInput(); }, PerformanceFilterInputTaker.MIN_DURATION_BETWEEN_MOUSE_MOVE_EVENTS_IN_MILLISECONDS);
        }
        noteKeyPress(key) {
            this.targetInputTaker.noteKeyPress(key);
        }
        noteKeyRelease(key) {
            this.targetInputTaker.noteKeyRelease(key);
        }
        noteKeyTyping(key) {
            this.targetInputTaker.noteKeyTyping(key);
        }
        noteLeftMouseButtonClick() {
            this.targetInputTaker.noteLeftMouseButtonClick();
        }
        noteLeftMouseButtonPress() {
            this.targetInputTaker.noteLeftMouseButtonPress();
        }
        noteLeftMouseButtonRelease() {
            this.targetInputTaker.noteLeftMouseButtonRelease();
        }
        noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            const currentTimeInMilliseconds = Date.now();
            if (currentTimeInMilliseconds >
                this.latestMouseMoveTime + PerformanceFilterInputTaker.MIN_DURATION_BETWEEN_MOUSE_MOVE_EVENTS_IN_MILLISECONDS) {
                this.noteMouseMoveWhenReady(cursorXPositionOnViewArea, cursorYPositionOnViewArea, currentTimeInMilliseconds);
            }
            else {
                this.latestSkippedInput =
                    new MouseInput_2.MouseInput(MouseInputType_4.MouseInputType.MOUSE_MOVE, cursorXPositionOnViewArea, cursorYPositionOnViewArea);
            }
        }
        noteMouseWheelClick() {
            this.targetInputTaker.noteMouseWheelClick();
        }
        noteMouseWheelPress() {
            this.targetInputTaker.noteMouseWheelPress();
        }
        noteMouseWheelRelease() {
            this.targetInputTaker.noteMouseWheelRelease();
        }
        noteMouseWheelRotationStep(rotationDirection) {
            this.targetInputTaker.noteMouseWheelRotationStep(rotationDirection);
        }
        noteResize(viewAreaWidth, viewAreaHeight) {
            this.targetInputTaker.noteResize(viewAreaWidth, viewAreaHeight);
        }
        noteRightMouseButtonClick() {
            this.targetInputTaker.noteRightMouseButtonClick();
        }
        noteRightMouseButtonPress() {
            this.targetInputTaker.noteRightMouseButtonPress;
        }
        noteRightMouseButtonRelease() {
            this.targetInputTaker.noteRightMouseButtonRelease();
        }
        noteMouseMoveWhenReady(cursorXPositionOnViewArea, cursorYPositionOnViewArea, currentTimeInMilliseconds) {
            this.latestMouseMoveTime = currentTimeInMilliseconds;
            this.latestSkippedInput = undefined;
            this.targetInputTaker.noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
        }
        runLatestSkippedInput() {
            if (this.latestSkippedInput instanceof MouseInput_2.MouseInput) {
                const mouseInput = this.latestSkippedInput;
                this.noteMouseMoveWhenReady(mouseInput.getCursorXPosition(), mouseInput.getCursorYPosition(), Date.now());
            }
        }
    }
    PerformanceFilterInputTaker.MIN_DURATION_BETWEEN_MOUSE_MOVE_EVENTS_IN_MILLISECONDS = 200;
    exports.PerformanceFilterInputTaker = PerformanceFilterInputTaker;
});
define("System/Application/GUIApplication/ReceiverController", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ReceiverController {
        constructor(runMethod, getDataMethod) {
            if (runMethod === null) {
                throw new Error('The given run method is null.');
            }
            if (runMethod === undefined) {
                throw new Error('The given run method is undefined.');
            }
            if (getDataMethod === null) {
                throw new Error('The given getData method is null.');
            }
            if (getDataMethod === undefined) {
                throw new Error('The given getData method is undefined.');
            }
            this.runMethod = runMethod;
            this.getDataMethod = getDataMethod;
        }
        getData(request) {
            return this.getDataMethod(request);
        }
        run(command) {
            this.runMethod(command);
        }
    }
    exports.ReceiverController = ReceiverController;
});
define("System/Application/GUIApplication/FrontCanvasGUIClient", ["require", "exports", "Core/Document/ChainedNode/ChainedNode", "System/Application/GUIApplication/FrontCanvasGUIClientInputTaker", "System/Application/GUIApplication/FrontCanvasGUIClientCommandProtocol", "System/Application/GUIApplication/FrontCanvasGUIClientObjectProtocol", "Core/CommonType/CommonTypeHelper/GlobalStringHelper", "System/Application/GUIApplication/GUIHandler", "Core/Net/EndPoint5/NetEndPoint5", "Core/Document/Node/Node", "System/GUI/Input/PerformanceFilterInputTaker", "System/Application/GUIApplication/ReceiverController", "Core/Container/SingleContainer"], function (require, exports, ChainedNode_2, FrontCanvasGUIClientInputTaker_1, FrontCanvasGUIClientCommandProtocol_2, FrontCanvasGUIClientObjectProtocol_2, GlobalStringHelper_1, GUIHandler_1, NetEndPoint5_1, Node_8, PerformanceFilterInputTaker_1, ReceiverController_1, SingleContainer_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClient {
        constructor(ip, port, optionalTarget, window) {
            this.ready = false;
            this.mGUIHandler =
                new GUIHandler_1.GUIHandler(window, new PerformanceFilterInputTaker_1.PerformanceFilterInputTaker(new FrontCanvasGUIClientInputTaker_1.FrontCanvasGUIClientInputTaker(i => this.noteInputOnCounterpart(i), () => this.getCursorXPositionOnViewArea(), () => this.getCursorYPositionOnViewArea())));
            this.endPoint = new NetEndPoint5_1.NetEndPoint5(ip, port, optionalTarget);
            this.endPoint.setReceiverController(new ReceiverController_1.ReceiverController(c => this.run(c), r => this.getData(r)));
        }
        static getOptionalTargetApplicationFromURL() {
            const lURL = this.getURLWithoutSlashAtEnd();
            const lURLParts = lURL.split('//')[1].split('/');
            switch (lURLParts.length) {
                case 1:
                    return SingleContainer_3.SingleContainer.withoutElement();
                case 2:
                    const target = GlobalStringHelper_1.GlobalStringHelper.createStringWithReplacedParts(lURLParts[1], '_', ' ');
                    return SingleContainer_3.SingleContainer.withElement(target);
                default:
                    throw new Error('The given URL \'' + lURL + '\' is not valid.');
            }
        }
        static getURLWithoutSlashAtEnd() {
            const lURL = window.location.href;
            if (lURL.endsWith('/')) {
                return lURL.substring(0, lURL.length - 1);
            }
            return lURL;
        }
        static toIpAndPortUsingWindow(ip, port, window) {
            return new FrontCanvasGUIClient(ip, port, SingleContainer_3.SingleContainer.withoutElement(), window);
        }
        static toIpAndPortAndApplicationUsingWindow(ip, port, application, window) {
            return new FrontCanvasGUIClient(ip, port, SingleContainer_3.SingleContainer.withElement(application), window);
        }
        static toIpAndPortAndApplicationFromURLUsingWindow(ip, port, window) {
            return new FrontCanvasGUIClient(ip, port, this.getOptionalTargetApplicationFromURL(), window);
        }
        getCursorXPositionOnViewArea() {
            return this.mGUIHandler.getCursorXPositionOnViewArea();
        }
        getCursorYPositionOnViewArea() {
            return this.mGUIHandler.getCursorYPositionOnViewArea();
        }
        noteInputOnCounterpart(input) {
            if (this.ready) {
                this.runOnConunterpart(ChainedNode_2.ChainedNode.withHeaderAndAttributeFromNode(FrontCanvasGUIClientCommandProtocol_2.FrontCanvasGUIClientCommandProtocol.NOTE_INPUT, input.getSpecification()));
            }
        }
        getData(request) {
            switch (request.getHeader()) {
                case FrontCanvasGUIClientObjectProtocol_2.FrontCanvasGUIClientObjectProtocol.GUI:
                    return this.getDataFromGUI(request.getNextNode());
                default:
                    throw new Error('The given request is not valid:' + request.toString());
            }
        }
        getDataFromGUI(pGUIDataRequest) {
            switch (pGUIDataRequest.getHeader()) {
                case FrontCanvasGUIClientObjectProtocol_2.FrontCanvasGUIClientObjectProtocol.VIEW_AREA_SIZE:
                    return Node_8.Node.fromNumberPair(this.mGUIHandler.getViewAreaSize());
                default:
                    throw new Error('The given GUI data request is not valid.');
            }
        }
        run(command) {
            switch (command.getHeader()) {
                case FrontCanvasGUIClientObjectProtocol_2.FrontCanvasGUIClientObjectProtocol.GUI:
                    this.mGUIHandler.runGUICommand(command.getNextNode());
                    break;
                case FrontCanvasGUIClientCommandProtocol_2.FrontCanvasGUIClientCommandProtocol.OPEN_NEW_TAB:
                    this.runOpenNewTabCommand(command);
                    break;
                case FrontCanvasGUIClientCommandProtocol_2.FrontCanvasGUIClientCommandProtocol.REDIRECT:
                    this.runRedirectCommand(command);
                    break;
                case FrontCanvasGUIClientCommandProtocol_2.FrontCanvasGUIClientCommandProtocol.SEND_OPTIONAL_FILE:
                    this.mGUIHandler.getOptionalFile(fileContainer => this.sendOptionalFile(fileContainer));
                    break;
                default:
                    throw new Error('The given command is not valid.');
            }
            this.ready = true;
        }
        openNewTabWithURL(pURL) {
            this.mGUIHandler.openNewTabWithURL(pURL);
        }
        redirectTo(pURL) {
            this.mGUIHandler.redirectTo(pURL);
        }
        runOnConunterpart(command) {
            if (this.endPoint !== undefined) {
                this.endPoint.run(command);
            }
        }
        runOpenNewTabCommand(openNewTabCommand) {
            const lURL = openNewTabCommand
                .getAttributes()
                .getRefFirstByCondition(a => a.hasGivenHeader(FrontCanvasGUIClientObjectProtocol_2.FrontCanvasGUIClientObjectProtocol.URL))
                .getOneAttribute()
                .getHeader();
            this.openNewTabWithURL(lURL);
        }
        runRedirectCommand(redirectCommand) {
            this.redirectTo(redirectCommand.getOneAttribute().getHeader());
        }
        sendOptionalFile(fileContainer) {
            if (fileContainer.isEmpty()) {
                this.runOnConunterpart(ChainedNode_2.ChainedNode.withHeader(FrontCanvasGUIClientCommandProtocol_2.FrontCanvasGUIClientCommandProtocol.RECEIVE_OPTIONAL_FILE));
            }
            else {
                this.runOnConunterpart(ChainedNode_2.ChainedNode.withHeaderAndAttribute(FrontCanvasGUIClientCommandProtocol_2.FrontCanvasGUIClientCommandProtocol.RECEIVE_OPTIONAL_FILE, ChainedNode_2.ChainedNode.withHeader(fileContainer.getRefElement())));
            }
        }
    }
    exports.FrontCanvasGUIClient = FrontCanvasGUIClient;
});
define("System/Application/WebApplicationProtocol/CommandProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CommandProtocol {
    }
    CommandProtocol.SET_ICON = 'SetIcon';
    CommandProtocol.SET_ROOT_HTML_ELEMENT = 'SetRootHTMLElement';
    CommandProtocol.SET_TITLE = 'SetTitle';
    exports.CommandProtocol = CommandProtocol;
});
define("SystemAPI/FrontendWebGUIAPI/IFrontendWebGUI", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("System/FrontendWebGUI/FrontendWebGUI", ["require", "exports", "Core/Document/ChainedNode/ChainedNode"], function (require, exports, ChainedNode_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontendWebGUI {
        static withEventTakerAndWindow(eventTaker, window) {
            return new FrontendWebGUI(eventTaker, window);
        }
        constructor(eventTaker, window) {
            if (eventTaker === null) {
                throw new Error('The given event taker is null.');
            }
            if (eventTaker === undefined) {
                throw new Error('The given event taker is undefined.');
            }
            if (window === null) {
                throw new Error('The given window is null.');
            }
            if (window === undefined) {
                throw new Error('The given window is undefined.');
            }
            this.eventTaker = eventTaker;
            this.window = window;
            this.rootElement = this.getRefRootElement();
        }
        getIcon() {
            return this.icon;
        }
        getTitle() {
            return this.title;
        }
        setIcon(icon) {
            if (icon === null) {
                throw new Error('The given icon is null.');
            }
            if (icon === undefined) {
                throw new Error('The given icon is undefined.');
            }
            const iconHTMLElement = this.window.document.getElementById('icon');
            iconHTMLElement.href = icon.toCanvas().toDataURL('image/png');
        }
        setRootHTMLElementFromString(rootHTMLElementAsString) {
            this.rootElement.innerHTML = rootHTMLElementAsString;
            this.setupActionsOfElement(this.rootElement);
        }
        setTitle(title) {
            if (title === null) {
                throw new Error('The given title is null.');
            }
            if (title === undefined) {
                throw new Error('The given title is undefined.');
            }
            if (title.length == 0) {
                throw new Error('The given title is empty.');
            }
            this.title = title;
            this.window.document.title = this.title;
        }
        createCommandFromString(string) {
            const parts = string.split('_');
            const controlCommand = parts[0];
            const controlId = parts[1];
            return ChainedNode_3.ChainedNode.fromString('GUI.ControlByFixedId(' + controlId + ').' + controlCommand);
        }
        getRefRootElement() {
            var rootElement = this.window.document.getElementById('rootElement');
            if (rootElement === null) {
                rootElement = this.window.document.createElement('div');
                rootElement.id = 'rootElement';
                this.window.document.body.appendChild(rootElement);
            }
            return rootElement;
        }
        setupActionsOfElement(element) {
            for (const c of element.children) {
                if (c instanceof HTMLElement) {
                    if (c.onclick !== null) {
                        const onclickAttribute = c.getAttribute('onclick');
                        const command = this.createCommandFromString(onclickAttribute);
                        c.onclick = () => this.takeEvent(command);
                    }
                }
                this.setupActionsOfElement(c);
            }
        }
        takeEvent(command) {
            this.eventTaker(command);
        }
    }
    exports.FrontendWebGUI = FrontendWebGUI;
});
define("System/Application/WebApplication/FrontendWebClientGUIManager", ["require", "exports", "System/Application/WebApplicationProtocol/CommandProtocol", "System/FrontendWebGUI/FrontendWebGUI", "System/GUI/Graphic/Image"], function (require, exports, CommandProtocol_1, FrontendWebGUI_1, Image_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontendWebClientGUIManager {
        static withEventTaker(eventTaker) {
            return new FrontendWebClientGUIManager(eventTaker);
        }
        constructor(eventTaker) {
            this.mFrontendWebGUI = FrontendWebGUI_1.FrontendWebGUI.withEventTakerAndWindow(eventTaker, window);
        }
        runGUICommand(pGUICommand) {
            switch (pGUICommand.getHeader()) {
                case CommandProtocol_1.CommandProtocol.SET_TITLE:
                    this.mFrontendWebGUI.setTitle(pGUICommand.getOneAttribute().getHeader());
                    break;
                case CommandProtocol_1.CommandProtocol.SET_ICON:
                    const icon = Image_2.Image.fromSpecification(pGUICommand.getOneAttributeAsNode());
                    this.mFrontendWebGUI.setIcon(icon);
                    break;
                case CommandProtocol_1.CommandProtocol.SET_ROOT_HTML_ELEMENT:
                    this.mFrontendWebGUI.setRootHTMLElementFromString(pGUICommand.getOneAttribute().getHeader());
                    break;
                default:
                    throw new Error('The given \'' + pGUICommand + '\' is not valid.');
            }
        }
    }
    exports.FrontendWebClientGUIManager = FrontendWebClientGUIManager;
});
define("System/Application/WebApplicationProtocol/ObjectProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ObjectProtocol {
    }
    ObjectProtocol.GUI = 'GUI';
    exports.ObjectProtocol = ObjectProtocol;
});
define("System/Application/WebApplication/FrontendWebClient", ["require", "exports", "System/Application/WebApplication/FrontendWebClientGUIManager", "Core/Net/EndPoint5/NetEndPoint5", "System/Application/WebApplicationProtocol/ObjectProtocol", "System/Application/GUIApplication/ReceiverController", "Core/Container/SingleContainer"], function (require, exports, FrontendWebClientGUIManager_1, NetEndPoint5_2, ObjectProtocol_1, ReceiverController_2, SingleContainer_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontendWebClient {
        static toIpAndPort(ip, port) {
            return new FrontendWebClient(ip, port, SingleContainer_4.SingleContainer.withoutElement());
        }
        constructor(ip, port, optionalTarget) {
            this.mGUIManager = FrontendWebClientGUIManager_1.FrontendWebClientGUIManager.withEventTaker((command) => this.takeEvent(command));
            this.endPoint = new NetEndPoint5_2.NetEndPoint5(ip, port, optionalTarget);
            this.endPoint.setReceiverController(new ReceiverController_2.ReceiverController(c => this.run(c), r => this.getData(r)));
        }
        getData(request) {
            throw new Error('The given request \'' + request + '\' not valid.');
        }
        run(command) {
            switch (command.getHeader()) {
                case ObjectProtocol_1.ObjectProtocol.GUI:
                    this.mGUIManager.runGUICommand(command.getNextNode());
                    break;
                default:
                    throw new Error('The given command \'' + command + '\' is not valid.');
            }
        }
        takeEvent(command) {
            this.endPoint.run(command);
        }
    }
    exports.FrontendWebClient = FrontendWebClient;
});
define("System/GUI/StructureProperty/DirectionInRectangleMapper", ["require", "exports", "SystemAPI/GUIAPI/StructureProperty/DirectionInRectangle"], function (require, exports, DirectionInRectangle_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class DirectionInRectangleMapper {
        static createDirectionInRectangleMapperFromSpecification(specification) {
            return DirectionInRectangle_3.DirectionInRectangle[specification.getOneAttributeHeader()];
        }
        constructor() { }
    }
    exports.DirectionInRectangleMapper = DirectionInRectangleMapper;
});

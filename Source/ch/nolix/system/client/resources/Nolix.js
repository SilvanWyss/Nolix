define("Common/Container/LinkedListNode", ["require", "exports"], function (require, exports) {
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
define("Common/Container/LinkedListIterator", ["require", "exports"], function (require, exports) {
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
define("Common/Container/LinkedList", ["require", "exports", "Common/Container/LinkedListIterator", "Common/Container/LinkedListNode"], function (require, exports, LinkedListIterator_1, LinkedListNode_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class LinkedList {
        constructor() {
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
        contains(element) {
            for (const e of this) {
                if (Object.is(e, element)) {
                    return true;
                }
            }
            return false;
        }
        contains2(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return true;
                }
            }
            return false;
        }
        containsAny() {
            return (this.elementCount > 0);
        }
        containsOne() {
            return (this.elementCount === 1);
        }
        forEach(elementTaker) {
            for (const e of this) {
                elementTaker(e);
            }
        }
        getOneAsString() {
            return this.getRefOne().toString();
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
        getRefFirstOrUndefined(selector) {
            for (const e of this) {
                if (selector(e)) {
                    return e;
                }
            }
            return undefined;
        }
        getRefLast() {
            if (this.elementCount === 0) {
                throw new Error("The current List is empty.");
            }
            return this.endNode.getRefElement();
        }
        getRefOne() {
            if (!this.containsOne()) {
                throw new Error('The current List contains none or several elements.');
            }
            return this.beginNode.getRefElement();
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
        isEmpty() {
            return (this.elementCount === 0);
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
        toString() {
            return this.toString2(',');
        }
        toString2(separator) {
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
        toStringInBrackets() {
            return ('(' + this.toString() + ')');
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
define("Common/BaseTest/BaseTest", ["require", "exports", "Common/Container/LinkedList"], function (require, exports, LinkedList_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BaseTest {
        constructor() {
            this.currentTestCaseErrors = new LinkedList_1.LinkedList();
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
define("Common/Node/Node", ["require", "exports", "Common/Container/LinkedList"], function (require, exports, LinkedList_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Node {
        constructor() {
            this.attributes = new LinkedList_2.LinkedList();
        }
        static createOriginStringFromReproducingString(reproducinString) {
            if (reproducinString === null) {
                throw new Error('The given reproducing string is null.');
            }
            if (reproducinString === undefined) {
                throw new Error('The given reproducing string is undefined.');
            }
            return reproducinString
                .replace(this.DOT_CODE, '.')
                .replace(this.COMMA_CODE, ',')
                .replace(this.OPEN_BRACKET_CODE, '(')
                .replace(this.CLOSED_BRACKET_CODE, ')')
                .replace(this.DOLLAR_SYMBOL_CODE, '$');
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
        static fromString(string) {
            return new Node().resetFrom(string);
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
        getOneAttributeAsString() {
            return this.getRefOneAttribute().toString();
        }
        getRefAttributes() {
            return this.attributes;
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
    Node.DOT_CODE = "$D";
    Node.COMMA_CODE = "$M";
    Node.DOLLAR_SYMBOL_CODE = "$X";
    Node.OPEN_BRACKET_CODE = "$O";
    Node.CLOSED_BRACKET_CODE = "$C";
    exports.Node = Node;
});
define("Common/ChainedNode/Task", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var Task;
    (function (Task) {
        Task[Task["DO_NOTHING"] = 0] = "DO_NOTHING";
        Task[Task["READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE"] = 1] = "READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE";
        Task[Task["READ_NEXT_NODE"] = 2] = "READ_NEXT_NODE";
    })(Task = exports.Task || (exports.Task = {}));
});
define("Common/ChainedNode/ChainedNode", ["require", "exports", "Common/Container/LinkedList", "Common/Node/Node", "Common/ChainedNode/Task"], function (require, exports, LinkedList_3, Node_1, Task_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ChainedNode {
        constructor() {
            this.header = undefined;
            this.nextNode = undefined;
            this.attributes = new LinkedList_3.LinkedList();
        }
        static createReproducingString(string) {
            return string
                .replace('$', ChainedNode.DOLLAR_SYMBOL_CODE)
                .replace('.', ChainedNode.DOT_CODE)
                .replace(',', ChainedNode.COMMA_CODE)
                .replace('(', ChainedNode.OPEN_BRACKET_CODE)
                .replace(')', ChainedNode.CLOSED_BRACKET_CODE);
        }
        static fromNode(node) {
            const chainedNode = new ChainedNode();
            if (node.hasHeader()) {
                chainedNode.setHeader(node.getHeader());
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
    ChainedNode.DOT_CODE = "$D";
    ChainedNode.COMMA_CODE = "$M";
    ChainedNode.DOLLAR_SYMBOL_CODE = "$X";
    ChainedNode.OPEN_BRACKET_CODE = "$O";
    ChainedNode.CLOSED_BRACKET_CODE = "$C";
    exports.ChainedNode = ChainedNode;
});
define("Common/CommonTypeHelper/StringHelper", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class StringHelper {
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
    }
    exports.StringHelper = StringHelper;
});
define("Common/Container/SingleContainer", ["require", "exports"], function (require, exports) {
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
define("Common/EndPoint2/NetEndPoint2", ["require", "exports"], function (require, exports) {
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
            console.log("The current NetEndPoint2 has receveived the message: " + message);
            if (message === null) {
                throw new Error('The given message is null.');
            }
            if (message === undefined) {
                throw new RTCError('The given message is undefined.');
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
            console.log("The current NetEndPoint2 sends the raw message: " + rawMessage);
            this.webSocket.send(rawMessage);
        }
    }
    NetEndPoint2.STANDARD_MESSAGE_PREFIX = 'M';
    NetEndPoint2.TARGET_MESSAGE_PREFIX = 'T';
    NetEndPoint2.CLEAR_TARGET_MESSAGE_PREFIX = 'A';
    exports.NetEndPoint2 = NetEndPoint2;
});
define("Common/EndPoint3/MessageRole", ["require", "exports"], function (require, exports) {
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
define("Common/EndPoint3/Package", ["require", "exports", "Common/EndPoint3/MessageRole"], function (require, exports, MessageRole_1) {
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
            console.log('The current Package has been created: ' + this.toString());
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
define("Common/EndPoint3/NetEndPoint3", ["require", "exports", "Common/Container/LinkedList", "Common/EndPoint3/MessageRole", "Common/EndPoint2/NetEndPoint2", "Common/EndPoint3/Package"], function (require, exports, LinkedList_4, MessageRole_2, NetEndPoint2_1, Package_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint3 {
        constructor(ip, port, optionalTarget) {
            this.messageIndex = 0;
            this.receivedPackages = new LinkedList_4.LinkedList();
            this.receive = (message) => {
                console.log('The current NetEndPoint3 has received the message: ' + message);
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
            console.log('The current NetEndPoint3 sends the message: ' + message);
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
                const package_ = this.receivedPackages.getRefFirstOrUndefined(rp => rp.hasIndex(index));
                if (package_ !== undefined) {
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
define("Common/EndPoint5/IDataProviderController", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Common/EndPoint5/NetEndPoint5Protocol", ["require", "exports"], function (require, exports) {
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
define("Common/EndPoint5/NetEndPoint5", ["require", "exports", "Common/ChainedNode/ChainedNode", "Common/Node/Node", "Common/Container/LinkedList", "Common/EndPoint3/NetEndPoint3", "Common/EndPoint5/NetEndPoint5Protocol"], function (require, exports, ChainedNode_1, Node_2, LinkedList_5, NetEndPoint3_1, NetEndPoint5Protocol_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint5 {
        constructor(ip, port, optionalTarget) {
            this.receiverController = undefined;
            this.receiveMessageAndGetReply = (message) => {
                console.log('The current NetEndPoint5 has received the message: ' + message);
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
                    throw new Error(reply.getOneAttributeAsString());
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
define("Common/Test/NumberMediator", ["require", "exports", "Common/Test/Mediator"], function (require, exports, Mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NumberMediator extends Mediator_1.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
    }
    exports.NumberMediator = NumberMediator;
});
define("Common/Test/StringMediator", ["require", "exports", "Common/Test/Mediator"], function (require, exports, Mediator_2) {
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
define("Common/Test/Test", ["require", "exports", "Common/BaseTest/BaseTest", "Common/Test/FunctionMediator", "Common/Test/Mediator", "Common/Test/NumberMediator", "Common/Test/StringMediator"], function (require, exports, BaseTest_1, FunctionMediator_1, Mediator_3, NumberMediator_1, StringMediator_1) {
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
define("Common/Test/Mediator", ["require", "exports"], function (require, exports) {
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
define("Common/Test/ThrownErrorMediator", ["require", "exports"], function (require, exports) {
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
define("Common/Test/FunctionMediator", ["require", "exports", "Common/Test/Mediator", "Common/Test/ThrownErrorMediator"], function (require, exports, Mediator_4, ThrownErrorMediator_1) {
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
define("Common/Test/TestPool", ["require", "exports", "Common/Container/LinkedList"], function (require, exports, LinkedList_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TestPool {
        constructor() {
            this.tests = new LinkedList_6.LinkedList();
            this.testPools = new LinkedList_6.LinkedList();
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
            if (this.testPools.contains(testPool)) {
                return true;
            }
            return this.testPools.contains2(ts => ts.containsRecursively(testPool));
        }
    }
    exports.TestPool = TestPool;
});
define("Element/Base/Element", ["require", "exports", "Common/Node/Node"], function (require, exports, Node_3) {
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
define("Element/Input/Key", ["require", "exports"], function (require, exports) {
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
        Key[Key["ARROW_UP"] = 61] = "ARROW_UP";
        Key[Key["ARROW_DOWN"] = 62] = "ARROW_DOWN";
        Key[Key["ARROW_LEFT"] = 63] = "ARROW_LEFT";
        Key[Key["ARROW_RIGHT"] = 64] = "ARROW_RIGHT";
        Key[Key["ALTERNATIVE"] = 65] = "ALTERNATIVE";
        Key[Key["BACKSPACE"] = 66] = "BACKSPACE";
        Key[Key["BREAK"] = 67] = "BREAK";
        Key[Key["CAPS_LOOK"] = 68] = "CAPS_LOOK";
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
define("Element/BaseGUI_API/IInputTaker", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Element/CanvasGUI/CanvasGUIProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BrowserGUIProtocol {
    }
    BrowserGUIProtocol.CREATE_PAINTER_HEADER = 'CreatePainter';
    BrowserGUIProtocol.PAINT_FILLED_RECTANGLE_HEADER = 'PaintFilledRectangle';
    BrowserGUIProtocol.PAINT_IMAGE_HEADER = 'PaintImage';
    BrowserGUIProtocol.PAINT_TEXT_HEADER = 'PaintText';
    BrowserGUIProtocol.SET_COLOR_HEADER = 'SetColor';
    BrowserGUIProtocol.TRANSLATE_HEADER = 'Translage';
    exports.BrowserGUIProtocol = BrowserGUIProtocol;
});
define("Element/Color/Color", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Color {
        constructor(redValue, greenValue, blueValue) {
            if (redValue < 0 || redValue > 255) {
                throw new Error('The given redValue is not valid.');
            }
            if (greenValue < 0 || greenValue > 255) {
                throw new Error('The given greenValue is not valid.');
            }
            if (blueValue < 0 || blueValue > 255) {
                throw new Error('The given blueValue is not valid.');
            }
            this.redValue = redValue;
            this.greenValue = greenValue;
            this.blueValue = blueValue;
        }
        static fromSpecification(specification) {
            return Color.fromString(specification.getOneAttributeAsString());
        }
        static fromString(string) {
            if (string === null) {
                throw new Error('The given string is null.');
            }
            if (string === undefined) {
                throw new Error('The given string is undefined.');
            }
            if (string.length !== 8) {
                throw new Error('The given string is not valid.');
            }
            return new Color(Number.parseInt('0x' + string.substr(2, 2)), Number.parseInt('0x' + string.substr(4, 2)), Number.parseInt('0x' + string.substr(6, 2)));
        }
        getHTMLCode() {
            return '#'
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.redValue)
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.greenValue)
                + this.getNumberAsHexadecimalStringWithLeadingZeros(this.blueValue);
        }
        getNumberAsHexadecimalStringWithLeadingZeros(number) {
            var string = number.toString(16).toUpperCase();
            while (string.length < 2) {
                string = '0' + string;
            }
            return string;
        }
    }
    Color.BLACK = new Color(0, 0, 0);
    Color.WHITE = new Color(255, 255, 255);
    exports.Color = Color;
});
define("Element/TextFormat/TextFormat", ["require", "exports", "Element/Color/Color"], function (require, exports, Color_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TextFormat {
        static fromSpecification(specification) {
            const attributes = specification.getRefAttributes();
            return new TextFormat(attributes.getRefAt(4).getOneAttributeAsNumber(), Color_1.Color.fromSpecification(attributes.getRefAt(5)));
        }
        constructor(textSize, textColor) {
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
            this.textSize = textSize;
            this.textColor = textColor;
        }
        getTextColor() {
            return this.textColor;
        }
        getTextSize() {
            return this.textSize;
        }
    }
    exports.TextFormat = TextFormat;
});
define("Element/CanvasGUI/CanvasGUIGlobalPainter", ["require", "exports", "Element/Color/Color", "Element/TextFormat/TextFormat"], function (require, exports, Color_2, TextFormat_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUIGlobalPainter {
        constructor(canvasRenderingContext) {
            if (canvasRenderingContext === null) {
                throw new Error('The given canvasRenderingContext is null.');
            }
            if (canvasRenderingContext === undefined) {
                throw new Error('The given canvasRenderingContext is undefined.');
            }
            this.canvasRenderingContext = canvasRenderingContext;
        }
        paintFilledRectangle(xPosition, yPosition, width, height) {
            this.canvasRenderingContext.fillRect(xPosition, yPosition, width, height);
        }
        paintText(xPosition, yPosition, text) {
            this.paintTextWithTextFormat(xPosition, yPosition, text, CanvasGUIGlobalPainter.DEFAULT_TEXT_FORMAT);
        }
        paintTextWithTextFormat(xPosition, yPosition, text, textFormat) {
            console.log('The current CanvasGUIGlobalPainter paints the text: \''
                + text
                + '\' at position ('
                + xPosition
                + ', '
                + yPosition
                + ').');
            this.canvasRenderingContext.textBaseline = 'top';
            this.canvasRenderingContext.font = textFormat.getTextSize() + 'px Arial';
            this.canvasRenderingContext.fillStyle = textFormat.getTextColor().getHTMLCode();
            this.canvasRenderingContext.fillText(text, xPosition, yPosition);
        }
        paintTextWithTextFormatAndMaxLength(xPosition, yPosition, text, textFormat, maxLength) {
            this.canvasRenderingContext.fillText(text, xPosition, yPosition);
        }
        setColor(color) {
            this.canvasRenderingContext.fillStyle = color.getHTMLCode();
        }
    }
    CanvasGUIGlobalPainter.DEFAULT_TEXT_SIZE = 10;
    CanvasGUIGlobalPainter.DEFAULT_TEXT_COLOR = Color_2.Color.BLACK;
    CanvasGUIGlobalPainter.DEFAULT_TEXT_FORMAT = new TextFormat_1.TextFormat(CanvasGUIGlobalPainter.DEFAULT_TEXT_SIZE, CanvasGUIGlobalPainter.DEFAULT_TEXT_COLOR);
    exports.CanvasGUIGlobalPainter = CanvasGUIGlobalPainter;
});
define("Element/PainterAPI/IPainter", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Element/CanvasGUI/CanvasGUIPainter", ["require", "exports", "Element/CanvasGUI/CanvasGUIGlobalPainter", "Common/Container/SingleContainer"], function (require, exports, CanvasGUIGlobalPainter_1, SingleContainer_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CanvasGUIPainter {
        static createPainterFor(canvasRenderingContext) {
            return new CanvasGUIPainter(0, 0, new CanvasGUIGlobalPainter_1.CanvasGUIGlobalPainter(canvasRenderingContext), SingleContainer_1.SingleContainer.EMPTY_CONTAINER);
        }
        constructor(xPosition, yPosition, globalPainter, parentPainterContainer) {
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
        }
        createPainter() {
            return new CanvasGUIPainter(0, 0, this.globalPainter, SingleContainer_1.SingleContainer.withElement(this));
        }
        createPainterWithTranslation(xTranslation, yTranslation) {
            return new CanvasGUIPainter(xTranslation, yTranslation, this.globalPainter, SingleContainer_1.SingleContainer.withElement(this));
        }
        createPainterWithTranslationAndPaintArea(xTranslation, yTranslation, paintAreaWidth, paintAreaHeight) {
            return this.createPainterWithTranslation(xTranslation, yTranslation);
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
        paintFilledRectangle(width, height) {
            this.globalPainter.paintFilledRectangle(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), width, height);
        }
        paintFilledRectangleAtPosition(xPosition, yPosition, width, height) {
            this.globalPainter.paintFilledRectangle(this.getXPositionOnViewArea() + xPosition, this.getYPositionOnViewArea() + yPosition, width, height);
        }
        paintText(text) {
            this.globalPainter.paintText(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), text);
        }
        paintTextWithTextFormat(text, textFormat) {
            this.globalPainter.paintTextWithTextFormat(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), text, textFormat);
        }
        paintTextWithTextFormatAndMaxLength(text, textFormat, maxLength) {
            this.globalPainter.paintTextWithTextFormatAndMaxLength(this.getXPositionOnViewArea(), this.getYPositionOnViewArea(), text, textFormat, maxLength);
        }
        setColor(color) {
            this.globalPainter.setColor(color);
        }
        translate(xTranslation, yTranslation) {
            this.xPosition += xTranslation;
            this.yPosition += yTranslation;
        }
    }
    exports.CanvasGUIPainter = CanvasGUIPainter;
});
define("Element/CanvasGUI/PaintProcess", ["require", "exports", "Common/Container/LinkedList"], function (require, exports, LinkedList_7) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class PaintProcess {
        constructor(initialPainter, paintCommands) {
            this.painters = new LinkedList_7.LinkedList();
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
define("Element/CanvasGUI/CanvasGUI", ["require", "exports", "Element/CanvasGUI/CanvasGUIProtocol", "Element/CanvasGUI/CanvasGUIPainter", "Element/Color/Color", "Common/Container/LinkedList", "Element/CanvasGUI/PaintProcess", "Element/TextFormat/TextFormat"], function (require, exports, CanvasGUIProtocol_1, CanvasGUIPainter_1, Color_3, LinkedList_8, PaintProcess_1, TextFormat_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BrowserGUI {
        constructor(window, inputTaker) {
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
            this.paintCommands = new LinkedList_8.LinkedList();
            this.window = window;
            this.canvas = window.document.createElement('canvas');
            this.window.document.body.appendChild(this.canvas);
            this.canvasRenderingContext2D = this.canvas.getContext('2d');
            this.window.onresize = () => this.noteResize();
            this.reset();
        }
        getTitle() {
            return this.title;
        }
        getViewAreaHeight() {
            return this.canvas.height;
        }
        getViewAreaWidth() {
            return this.canvas.width;
        }
        noteResize() {
            this.canvas.width = this.window.document.body.clientWidth;
            this.canvas.height = this.window.document.body.clientHeight;
            this.inputTaker.noteResize(this.getViewAreaWidth(), this.getViewAreaHeight());
        }
        refresh() {
            console.log('The current CanvasGUI refreshes.');
            this.canvasRenderingContext2D.clearRect(0, 0, this.getViewAreaWidth(), this.getViewAreaHeight());
            const painter = CanvasGUIPainter_1.CanvasGUIPainter.createPainterFor(this.canvasRenderingContext2D);
            this.paintBackground(painter);
            new PaintProcess_1.PaintProcess(painter, this.paintCommands);
        }
        reset() {
            this.setTitle(BrowserGUI.DEFAULT_TITLE);
            this.paintCommands.clear();
            this.refresh();
        }
        setPaintCommands(paintCommands) {
            this.paintCommands.refill(paintCommands);
        }
        setTextualPaintCommands(textualPaintCommands) {
            console.log('The current BrowserGUI sets the given textualPaintCommands \'' + textualPaintCommands + '\'');
            this.setPaintCommands(textualPaintCommands.to(tpc => this.createPaintCommand(tpc)));
        }
        setTitle(title) {
            console.log('The current BrowserGUI sets the title: ' + title);
            if (title === null) {
                throw new Error('The given title is null.');
            }
            if (title === undefined) {
                throw new Error('The given title is undefined.');
            }
            this.title = title;
            this.window.document.title = this.title;
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
                case CanvasGUIProtocol_1.BrowserGUIProtocol.CREATE_PAINTER_HEADER:
                    return this.createCreatePainterCommand(painterIndex, textualPaintCommand);
                case CanvasGUIProtocol_1.BrowserGUIProtocol.PAINT_FILLED_RECTANGLE_HEADER:
                    return this.createPaintFilledRectangleCommand(painterIndex, textualPaintCommand);
                case CanvasGUIProtocol_1.BrowserGUIProtocol.PAINT_TEXT_HEADER:
                    return this.createPaintTextCommand(painterIndex, textualPaintCommand);
                case CanvasGUIProtocol_1.BrowserGUIProtocol.SET_COLOR_HEADER:
                    return this.createSetColorCommand(painterIndex, textualPaintCommand);
                case CanvasGUIProtocol_1.BrowserGUIProtocol.TRANSLATE_HEADER:
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
        createPaintTextCommand(painterIndex, textualPaintTextCommand) {
            const text = textualPaintTextCommand.getFirstAttributeAsString();
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
            const color = Color_3.Color.fromSpecification(textualSetColorCommand.getOneAttributeAsNode());
            return pp => pp.getRefPainterByIndex(painterIndex).setColor(color);
        }
        createTranslateCommand(painterIndex, translateCommand) {
            const attributes = translateCommand.getAttributes();
            const xTranslation = attributes.getRefAt(1).toNumber();
            const yTranslation = attributes.getRefAt(2).toNumber();
            return pp => pp.getRefPainterByIndex(painterIndex).translate(xTranslation, yTranslation);
        }
        paintBackground(painter) {
            painter.setColor(BrowserGUI.BACKGROUND_COLOR);
            painter.paintFilledRectangle(this.getViewAreaWidth(), this.getViewAreaHeight());
        }
    }
    BrowserGUI.DEFAULT_TITLE = 'GUI';
    BrowserGUI.BACKGROUND_COLOR = Color_3.Color.WHITE;
    exports.BrowserGUI = BrowserGUI;
});
define("Element/Input/Input", ["require", "exports", "Element/Base/Element"], function (require, exports, Element_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Input extends Element_1.Element {
    }
    exports.Input = Input;
});
define("Element/Input/KeyInputType", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var KeyInputType;
    (function (KeyInputType) {
        KeyInputType[KeyInputType["Press"] = 0] = "Press";
        KeyInputType[KeyInputType["Release"] = 1] = "Release";
        KeyInputType[KeyInputType["Typing"] = 2] = "Typing";
    })(KeyInputType = exports.KeyInputType || (exports.KeyInputType = {}));
});
define("Element/Input/KeyInput", ["require", "exports", "Element/Input/Input", "Element/Input/Key", "Element/Input/KeyInputType", "Common/Container/LinkedList", "Common/Node/Node"], function (require, exports, Input_1, Key_1, KeyInputType_1, LinkedList_9, Node_4) {
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
            const attributes = new LinkedList_9.LinkedList();
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
define("Element/Input/MouseInputEnum", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var MouseInputEnum;
    (function (MouseInputEnum) {
        MouseInputEnum[MouseInputEnum["LeftMouseButtonPress"] = 0] = "LeftMouseButtonPress";
        MouseInputEnum[MouseInputEnum["LeftMouseButtonRelease"] = 1] = "LeftMouseButtonRelease";
        MouseInputEnum[MouseInputEnum["LeftMouseButtonClick"] = 2] = "LeftMouseButtonClick";
        MouseInputEnum[MouseInputEnum["RightMouseButtonPress"] = 3] = "RightMouseButtonPress";
        MouseInputEnum[MouseInputEnum["RightMouseButtonRelease"] = 4] = "RightMouseButtonRelease";
        MouseInputEnum[MouseInputEnum["RightMouseButtonClick"] = 5] = "RightMouseButtonClick";
        MouseInputEnum[MouseInputEnum["MouseWheelPress"] = 6] = "MouseWheelPress";
        MouseInputEnum[MouseInputEnum["MouseWheelRelease"] = 7] = "MouseWheelRelease";
        MouseInputEnum[MouseInputEnum["MouseWheelClick"] = 8] = "MouseWheelClick";
        MouseInputEnum[MouseInputEnum["ForwardMouseWheelRotationStep"] = 9] = "ForwardMouseWheelRotationStep";
        MouseInputEnum[MouseInputEnum["BackwardMouseWheelRotationStep"] = 10] = "BackwardMouseWheelRotationStep";
    })(MouseInputEnum = exports.MouseInputEnum || (exports.MouseInputEnum = {}));
});
define("Element/Input/MouseInput", ["require", "exports", "Element/Input/Input", "Common/Container/LinkedList", "Element/Input/MouseInputEnum", "Common/Node/Node"], function (require, exports, Input_2, LinkedList_10, MouseInputEnum_1, Node_5) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class MouseInput extends Input_2.Input {
        constructor(mouseInputEnum) {
            super();
            if (mouseInputEnum === null) {
                throw new Error('The given mouseInputEnum is null.');
            }
            if (mouseInputEnum === undefined) {
                throw new Error('The given mouseInputEnum is undefined.');
            }
            this.mouseInputEnum = mouseInputEnum;
        }
        getAttributes() {
            return LinkedList_10.LinkedList.withElement(Node_5.Node.withHeader(MouseInputEnum_1.MouseInputEnum[this.mouseInputEnum]));
        }
        getType() {
            return MouseInput.TYPE_HEADER;
        }
        toEnum() {
            return this.mouseInputEnum;
        }
    }
    MouseInput.TYPE_HEADER = 'MouseInput';
    exports.MouseInput = MouseInput;
});
define("System/FrontCanvasGUIClient/FrontCanvasGUIClientInputTaker", ["require", "exports", "Element/Input/KeyInput", "Element/Input/KeyInputType", "Element/Input/MouseInput", "Element/Input/MouseInputEnum"], function (require, exports, KeyInput_1, KeyInputType_2, MouseInput_1, MouseInputEnum_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClientInputTaker {
        constructor(parentFrontCanvasGUIClient) {
            if (parentFrontCanvasGUIClient === null) {
                throw new Error('The given parentFrontCanvasGUIClient is null.');
            }
            if (parentFrontCanvasGUIClient === undefined) {
                throw new Error('The given parentFrontCanvasGUIClient is undefined.');
            }
            this.parentFrontCanvasGUIClient = parentFrontCanvasGUIClient;
        }
        noteKeyPress(key) {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new KeyInput_1.KeyInput(key, KeyInputType_2.KeyInputType.Press));
        }
        noteKeyRelease(key) {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new KeyInput_1.KeyInput(key, KeyInputType_2.KeyInputType.Release));
        }
        noteKeyTyping(key) {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new KeyInput_1.KeyInput(key, KeyInputType_2.KeyInputType.Typing));
        }
        noteLeftMouseButtonClick() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.LeftMouseButtonClick));
        }
        noteLeftMouseButtonPress() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.LeftMouseButtonPress));
        }
        noteLeftMouseButtonRelease() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.LeftMouseButtonRelease));
        }
        noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            this.parentFrontCanvasGUIClient.noteMouseMoveOnCounterpart(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
        }
        noteMouseWheelClick() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.MouseWheelClick));
        }
        noteMouseWheelPress() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.MouseWheelPress));
        }
        noteMouseWheelRelease() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.MouseWheelRelease));
        }
        noteResize(viewAreaWidth, viewAreaHeight) {
            this.parentFrontCanvasGUIClient.noteResizeOnCounterpart(viewAreaWidth, viewAreaHeight);
        }
        noteRightMouseButtonClick() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.RightMouseButtonClick));
        }
        noteRightMouseButtonPress() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.RightMouseButtonPress));
        }
        noteRightMouseButtonRelease() {
            this.parentFrontCanvasGUIClient.noteInputOnCounterpart(new MouseInput_1.MouseInput(MouseInputEnum_2.MouseInputEnum.RightMouseButtonRelease));
        }
    }
    exports.FrontCanvasGUIClientInputTaker = FrontCanvasGUIClientInputTaker;
});
define("System/FrontCanvasGUIClient/FrontCanvasGUIClientProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClientProtocol {
    }
    FrontCanvasGUIClientProtocol.GUI_HEADER = 'GUI';
    FrontCanvasGUIClientProtocol.NOTE_INPUT_HEADER = 'NoteInput';
    FrontCanvasGUIClientProtocol.NOTE_MOUSE_MOVE_HEADER = 'NoteMouseMove';
    FrontCanvasGUIClientProtocol.NOTE_RESIZE_HEADER = "NoteResize";
    FrontCanvasGUIClientProtocol.SET_PAINT_COMMANDS_HEADER = 'SetPaintCommands';
    FrontCanvasGUIClientProtocol.SET_TITLE_HEADER = "SetTitle";
    exports.FrontCanvasGUIClientProtocol = FrontCanvasGUIClientProtocol;
});
define("System/FrontCanvasGUIClient/GUIHandler", ["require", "exports", "Element/CanvasGUI/CanvasGUI", "System/FrontCanvasGUIClient/FrontCanvasGUIClientProtocol"], function (require, exports, CanvasGUI_1, FrontCanvasGUIClientProtocol_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class GUIHandler {
        constructor(window, eventTaker) {
            this.mGUI = new CanvasGUI_1.BrowserGUI(window, eventTaker);
        }
        canRunCommand(command) {
            return (command.hasHeader() && this.canRunCommandOfType(command.getHeader()));
        }
        canRunCommandOfType(type) {
            switch (type) {
                case FrontCanvasGUIClientProtocol_1.FrontCanvasGUIClientProtocol.GUI_HEADER:
                    return true;
                default:
                    return false;
            }
        }
        runGUICommand(pGUICommand) {
            console.log('The current GUIHandler runs the given pGUICommand: ' + pGUICommand);
            switch (pGUICommand.getHeader()) {
                case FrontCanvasGUIClientProtocol_1.FrontCanvasGUIClientProtocol.SET_TITLE_HEADER:
                    this.mGUI.setTitle(pGUICommand.getOneAttributeAsString());
                    this.mGUI.refresh();
                    break;
                case FrontCanvasGUIClientProtocol_1.FrontCanvasGUIClientProtocol.SET_PAINT_COMMANDS_HEADER:
                    this.setPaintCommands(pGUICommand.getAttributes());
                    this.mGUI.refresh();
                    break;
                default:
                    throw new Error('The given pGUICommand is not valid: ' + pGUICommand);
            }
        }
        setPaintCommands(paintCommands) {
            console.log('The current GUIHandler sets the given paintCommands: ' + paintCommands);
            this.mGUI.setTextualPaintCommands(paintCommands);
        }
    }
    exports.GUIHandler = GUIHandler;
});
define("System/FrontCanvasGUIClient/ReceiverController", ["require", "exports"], function (require, exports) {
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
define("System/FrontCanvasGUIClient/FrontCanvasGUIClient", ["require", "exports", "Common/ChainedNode/ChainedNode", "System/FrontCanvasGUIClient/FrontCanvasGUIClientInputTaker", "System/FrontCanvasGUIClient/FrontCanvasGUIClientProtocol", "System/FrontCanvasGUIClient/GUIHandler", "Common/Container/LinkedList", "Common/EndPoint5/NetEndPoint5", "Common/Node/Node", "System/FrontCanvasGUIClient/ReceiverController", "Common/Container/SingleContainer"], function (require, exports, ChainedNode_2, FrontCanvasGUIClientInputTaker_1, FrontCanvasGUIClientProtocol_2, GUIHandler_1, LinkedList_11, NetEndPoint5_1, Node_6, ReceiverController_1, SingleContainer_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontCanvasGUIClient {
        constructor(ip, port, optionalTarget, window) {
            this.mGUIHandler = new GUIHandler_1.GUIHandler(window, new FrontCanvasGUIClientInputTaker_1.FrontCanvasGUIClientInputTaker(this));
            this.endPoint = new NetEndPoint5_1.NetEndPoint5(ip, port, optionalTarget);
            this.endPoint.setReceiverController(new ReceiverController_1.ReceiverController(c => this.run(c), r => this.getData(r)));
        }
        static withIpAndNumberAndWindow(ip, port, window) {
            return new FrontCanvasGUIClient(ip, port, SingleContainer_2.SingleContainer.EMPTY_CONTAINER, window);
        }
        getGUIType() {
            return FrontCanvasGUIClient.GUI_TYPE;
        }
        noteInputOnCounterpart(input) {
            this.runOnConunterpart(ChainedNode_2.ChainedNode.withHeaderAndAttributeFromNode(FrontCanvasGUIClientProtocol_2.FrontCanvasGUIClientProtocol.NOTE_INPUT_HEADER, input.getSpecification()));
        }
        noteMouseMoveOnCounterpart(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            const attributes = new LinkedList_11.LinkedList();
            attributes.addAtEnd(ChainedNode_2.ChainedNode.withHeaderFromNumber(cursorXPositionOnViewArea));
            attributes.addAtEnd(ChainedNode_2.ChainedNode.withHeaderFromNumber(cursorYPositionOnViewArea));
            this.runOnConunterpart(ChainedNode_2.ChainedNode.withHeaderAndAttributes(FrontCanvasGUIClientProtocol_2.FrontCanvasGUIClientProtocol.NOTE_MOUSE_MOVE_HEADER, attributes));
        }
        noteResizeOnCounterpart(viewAreaWidth, viewAreaHeight) {
            const attributes = new LinkedList_11.LinkedList();
            attributes.addAtEnd(Node_6.Node.withHeaderFromNumber(viewAreaWidth));
            attributes.addAtEnd(Node_6.Node.withHeaderFromNumber(viewAreaHeight));
            this.runOnConunterpart(ChainedNode_2.ChainedNode.withHeaderAndAttributesFromNodes(FrontCanvasGUIClientProtocol_2.FrontCanvasGUIClientProtocol.NOTE_RESIZE_HEADER, attributes));
        }
        getData(request) {
            console.log('FrontCanvasGUIClient has received the request: ' + request.toString());
            switch (request.getHeader()) {
                case 'GUI_Type':
                    return Node_6.Node.withHeader(this.getGUIType());
                default:
                    throw new Error('The given request is not valid:' + request.toString());
            }
        }
        run(command) {
            console.log('FrontCanvasGUIClient runs the command: ' + command.toString());
            switch (command.getHeader()) {
                case FrontCanvasGUIClientProtocol_2.FrontCanvasGUIClientProtocol.GUI_HEADER:
                    this.mGUIHandler.runGUICommand(command.getNextNode());
                    break;
                default:
                    throw new Error('The given command is not valid.');
            }
        }
        runOnConunterpart(command) {
            if (this.endPoint !== undefined) {
                this.endPoint.run(command);
            }
        }
    }
    FrontCanvasGUIClient.GUI_TYPE = 'CanvasGUI';
    exports.FrontCanvasGUIClient = FrontCanvasGUIClient;
});

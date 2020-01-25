define("Core/Container/ListNode", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ListNode {
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
    exports.ListNode = ListNode;
});
define("Core/Container/ListIterator", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ListIterator {
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
    exports.ListIterator = ListIterator;
});
define("Core/Container/List", ["require", "exports", "Core/Container/ListIterator", "Core/Container/ListNode"], function (require, exports, ListIterator_1, ListNode_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class List {
        constructor() {
            this.elementCount = 0;
            this.beginNode = undefined;
            this.endNode = undefined;
        }
        addAtBegin(element) {
            const newNode = new ListNode_1.ListNode(element);
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
            const newNode = new ListNode_1.ListNode(element);
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
            const selectedElements = new List();
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
            const list = new List();
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
            const strings = new List();
            for (const e of this) {
                strings.addAtEnd(e.toString());
            }
            return strings;
        }
        [Symbol.iterator]() {
            return new ListIterator_1.ListIterator(this.beginNode);
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
    exports.List = List;
});
define("Core/BaseTest/BaseTest", ["require", "exports", "Core/Container/List"], function (require, exports, List_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BaseTest {
        constructor() {
            this.currentTestCaseErrors = new List_1.List();
        }
        run() {
            console.log(this.constructor.name);
            var prototype = this;
            var properties = [];
            do {
                properties = properties.concat(Object.getOwnPropertyNames(prototype));
            } while (prototype = Object.getPrototypeOf(prototype));
            for (const p of properties) {
                const name = p;
                if (name.startsWith('testCase_')) {
                    try {
                        this[name]();
                        if (this.currentTestCaseErrors.isEmpty()) {
                            console.log('PASSED: ' + name);
                        }
                        else {
                            console.log('FAILED: ' + name);
                        }
                    }
                    catch (error) {
                        console.log('FAILED ' + name + ': ' + error);
                    }
                    var i = 1;
                    for (const e of this.currentTestCaseErrors) {
                        console.log('   ' + i.toString() + ') ' + e);
                        i++;
                    }
                    this.currentTestCaseErrors.clear();
                }
            }
            console.log();
        }
        internal_addErrorToCurrentTestCase(error) {
            this.currentTestCaseErrors.addAtEnd(error);
        }
    }
    exports.BaseTest = BaseTest;
});
define("Core/Node/Node", ["require", "exports", "Core/Container/List"], function (require, exports, List_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Node {
        constructor() {
            this.attributes = new List_2.List();
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
            const documentNode = new Node();
            documentNode.set(string);
            return documentNode;
        }
        static withHeader(header) {
            const node = new Node();
            node.setHeader(header);
            return node;
        }
        static withNumberHeader(header) {
            return Node.withHeader(header.toString());
        }
        addAttribute(attribute) {
            this.attributes.addAtEnd(attribute.getCopy());
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
        }
        removeHeader() {
            this.header = undefined;
        }
        reset() {
            this.removeHeader();
            this.removeAttributes();
        }
        set(string) {
            this.reset();
            if (this.setAndGetEndIndex(string, 0) !== string.length - 1) {
                throw new Error('The given string does not represent a document node.');
            }
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
define("Core/ChainedNode/Task", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var Task;
    (function (Task) {
        Task[Task["DO_NOTHING"] = 0] = "DO_NOTHING";
        Task[Task["READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE"] = 1] = "READ_ATTRIBUTES_AND_CHECK_FOR_NEXT_NODE";
        Task[Task["READ_NEXT_NODE"] = 2] = "READ_NEXT_NODE";
    })(Task = exports.Task || (exports.Task = {}));
});
define("Core/ChainedNode/ChainedNode", ["require", "exports", "Core/Container/List", "Core/Node/Node", "Core/ChainedNode/Task"], function (require, exports, List_3, Node_1, Task_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ChainedNode {
        constructor() {
            this.header = undefined;
            this.nextNode = undefined;
            this.attributes = new List_3.List();
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
                this.attributes.addAtEnd(ChainedNode.fromNode(a));
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
define("Core/EndPoint2/NetEndPoint2", ["require", "exports"], function (require, exports) {
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
define("Core/EndPoint3/MessageRole", ["require", "exports"], function (require, exports) {
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
define("Core/EndPoint3/Package", ["require", "exports", "Core/EndPoint3/MessageRole"], function (require, exports, MessageRole_1) {
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
define("Core/EndPoint3/NetEndPoint3", ["require", "exports", "Core/Container/List", "Core/EndPoint3/MessageRole", "Core/EndPoint2/NetEndPoint2", "Core/EndPoint3/Package"], function (require, exports, List_4, MessageRole_2, NetEndPoint2_1, Package_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NetEndPoint3 {
        constructor(ip, port, optionalTarget) {
            this.messageIndex = 0;
            this.receivedPackages = new List_4.List();
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
            const receivedPackage = this.waitToAndGetAndRemoveReceivedPackage(messageIndex);
            switch (receivedPackage.getMessageRole()) {
                case MessageRole_2.MessageRole.REPLY_MESSAGE:
                    return receivedPackage.getMessage();
                case MessageRole_2.MessageRole.ERROR_MESSAGE:
                    throw new Error(receivedPackage.getMessage());
                default:
                    throw Error('The received packge is not valid.');
            }
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
    NetEndPoint3.TIMEOUT_IN_MILLISECONDS = 50000000;
    exports.NetEndPoint3 = NetEndPoint3;
});
define("Core/EndPoint5/IDataProviderController", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Core/EndPoint5/NetEndPoint5Protocol", ["require", "exports"], function (require, exports) {
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
define("Core/EndPoint5/NetEndPoint5", ["require", "exports", "Core/ChainedNode/ChainedNode", "Core/Node/Node", "Core/Container/List", "Core/EndPoint3/NetEndPoint3", "Core/EndPoint5/NetEndPoint5Protocol"], function (require, exports, ChainedNode_1, Node_2, List_5, NetEndPoint3_1, NetEndPoint5Protocol_1) {
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
            const commands = new List_5.List();
            commands.addAtEnd(command);
            this.runCommands(commands);
        }
        runCommands(commands) {
            const message = NetEndPoint5Protocol_1.NetEndPoint5Protocol.COMMANDS_HEADER + commands.toStringInBrackets();
            const reply = new Node_2.Node();
            reply.set(this.internalNetEndPoint.sendAndGetReply(message));
            switch (reply.getHeader()) {
                case 'Done':
                    break;
                case 'Error':
                    throw new Error(reply.getOneAttributeAsString());
            }
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
define("Core/Helper/StringHelper", ["require", "exports"], function (require, exports) {
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
define("Core/Test/NumberMediator", ["require", "exports", "Core/Test/Mediator"], function (require, exports, Mediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NumberMediator extends Mediator_1.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
    }
    exports.NumberMediator = NumberMediator;
});
define("Core/Test/StringMediator", ["require", "exports", "Core/Test/Mediator"], function (require, exports, Mediator_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class StringMediator extends Mediator_2.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
        isNotEmpty() {
            if (this.argument.length < 1) {
                this.test.internal_addErrorToCurrentTestCase('A string, that is not empty, was expected, but an empty string was received.');
            }
        }
    }
    exports.StringMediator = StringMediator;
});
define("Core/Test/Test", ["require", "exports", "Core/BaseTest/BaseTest", "Core/Test/FunctionMediator", "Core/Test/NumberMediator", "Core/Test/StringMediator"], function (require, exports, BaseTest_1, FunctionMediator_1, NumberMediator_1, StringMediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class Test extends BaseTest_1.BaseTest {
        expect(argument) {
            if (!argument) {
                this.internal_addErrorToCurrentTestCase('True was expected, but false was received.');
            }
        }
        expectFunction(argument) {
            return new FunctionMediator_1.FunctionMediator(this, argument);
        }
        expectNot(argument) {
            if (argument) {
                this.internal_addErrorToCurrentTestCase('False was expected, but true was received.');
            }
        }
        expectNotThisCase() {
            this.internal_addErrorToCurrentTestCase('The current case was not expected, but reached.');
        }
        expectNumber(argument) {
            return new NumberMediator_1.NumberMediator(this, argument);
        }
        expectString(argument) {
            return new StringMediator_1.StringMediator(this, argument);
        }
    }
    exports.Test = Test;
});
define("Core/Test/Mediator", ["require", "exports"], function (require, exports) {
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
                this.test.internal_addErrorToCurrentTestCase('An object that equals ' + value.toString() + ' was expected, but ' + this.argument.toString() + ' was received.');
            }
        }
        fulfills(condition) {
            if (!condition(this.argument)) {
                this.test.internal_addErrorToCurrentTestCase('An object, that fulfills the given condition, was expected, but an object, that does not fulfill it, was received.');
            }
        }
        isNotNullOrUndefined() {
            if (this.argument === null) {
                this.test.internal_addErrorToCurrentTestCase('An object was expected, but null was received.');
            }
            if (this.argument === undefined) {
                this.test.internal_addErrorToCurrentTestCase('An object was exected, but undefined was received.');
            }
        }
        isSameAs(object) {
            if (!Object.is(this.argument, object)) {
                this.test.internal_addErrorToCurrentTestCase('The given object was expected, but another object was received.');
            }
        }
    }
    exports.Mediator = Mediator;
});
define("Core/Test/ThrownErrorMediator", ["require", "exports"], function (require, exports) {
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
                this.test.internal_addErrorToCurrentTestCase('An error with the message \''
                    + message
                    + '\' was expected, but an error with the message \''
                    + this.error.message
                    + '\' was received.');
            }
        }
    }
    exports.ThrownErrorMediator = ThrownErrorMediator;
});
define("Core/Test/FunctionMediator", ["require", "exports", "Core/Test/Mediator", "Core/Test/ThrownErrorMediator"], function (require, exports, Mediator_3, ThrownErrorMediator_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FunctionMediator extends Mediator_3.Mediator {
        constructor(test, argument) {
            super(test, argument);
        }
        doesNotThrowError() {
            try {
                this.argument();
            }
            catch (error) {
                this.test.internal_addErrorToCurrentTestCase('There was not expected any error, but there was thrown an error.');
            }
        }
        throwsError() {
            try {
                this.argument();
                this.test.internal_addErrorToCurrentTestCase('An error was expected, but there was not thrown any error.');
            }
            catch (error) {
                return new ThrownErrorMediator_1.ThrownErrorMediator(this.test, error);
            }
        }
    }
    exports.FunctionMediator = FunctionMediator;
});
define("Core/Test/TestPool", ["require", "exports", "Core/Container/List"], function (require, exports, List_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class TestPool {
        constructor() {
            this.tests = new List_6.List();
            this.testPools = new List_6.List();
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
define("CoreTest/ChainedNodeTest/ChainedNodeTest", ["require", "exports", "Core/ChainedNode/ChainedNode", "Core/Test/Test"], function (require, exports, ChainedNode_2, Test_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ChainedNodeTest extends Test_1.Test {
        testCase_fromString_1A() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("");
            this.expectNot(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("");
        }
        testCase_fromString_1B() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a");
        }
        testCase_fromString_1C() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b)");
        }
        testCase_fromString_2A() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a.b");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.b");
        }
        testCase_fromString_2B() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b).c");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b).c");
        }
        testCase_fromString_2C() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a.b(c)");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.b(c)");
        }
        testCase_fromString_2D() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("(a.b).c");
            this.expectNot(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("(a.b).c");
        }
        testCase_fromString_2E() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a.(b.c)");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.(b.c)");
        }
        testCase_fromString_2F() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a.b.c");
            this.expect(chainedNode.hasHeader());
            this.expectNot(chainedNode.containsAttributes());
            this.expect(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a.b.c");
        }
        testCase_fromString_3A() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b,c,d)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b,c,d)");
        }
        testCase_fromString_3B() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b(c),d(e),f(g))");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b(c),d(e),f(g))");
        }
        testCase_fromString_3C() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b.c,d.e,f.g)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b.c,d.e,f.g)");
        }
        testCase_fromString_3D() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b(c).d,e(f).g,h(i).j)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b(c).d,e(f).g,h(i).j)");
        }
        testCase_fromString_3E() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b.c(d),e.(f.g),h.(i,j))");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b.c(d),e.(f.g),h.(i,j))");
        }
        testCase_fromString_3F() {
            const chainedNode = ChainedNode_2.ChainedNode.fromString("a(b.c.d,e.f.g,h.i.j)");
            this.expect(chainedNode.hasHeader());
            this.expect(chainedNode.containsAttributes());
            this.expectNot(chainedNode.hasNextNode());
            this.expectString(chainedNode.toString()).equals("a(b.c.d,e.f.g,h.i.j)");
        }
    }
    exports.ChainedNodeTest = ChainedNodeTest;
});
define("CoreTest/ChainedNodeTest/ChainedNodeTestPool", ["require", "exports", "CoreTest/ChainedNodeTest/ChainedNodeTest", "Core/Test/TestPool"], function (require, exports, ChainedNodeTest_1, TestPool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ChainedNodeTestPool extends TestPool_1.TestPool {
        constructor() {
            super();
            this.addTest(new ChainedNodeTest_1.ChainedNodeTest());
        }
    }
    exports.ChainedNodeTestPool = ChainedNodeTestPool;
});
define("CoreTest/ContainerTest/ListTest", ["require", "exports", "Core/Container/List", "Core/Test/Test"], function (require, exports, List_7, Test_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ListTest extends Test_2.Test {
        testCase_addAtBegin_WhenNullIsGiven() {
            const list = new List_7.List();
            this.expectFunction(() => list.addAtBegin(null)).throwsError();
        }
        testCase_addAtEnd() {
            const list = new List_7.List();
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            this.expectNumber(list.getSize()).equals(3);
            this.expectString(list.getRefAt(1)).isSameAs(apple);
            this.expectString(list.getRefAt(2)).isSameAs(banana);
            this.expectString(list.getRefAt(3)).isSameAs(cerish);
        }
        testCase_addAtEnd_WhenNullIsGiven() {
            const list = new List_7.List();
            this.expectFunction(() => list.addAtEnd(null)).throwsError();
        }
        testCase_clear() {
            const list = new List_7.List();
            list.addAtEnd('apple');
            list.addAtEnd('banana');
            list.addAtEnd('cerish');
            this.expectNumber(list.getSize()).equals(3);
            list.clear();
            this.expect(list.isEmpty());
        }
        testCase_constructor() {
            const list = new List_7.List();
            this.expect(list.isEmpty());
        }
        testCase_contains() {
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            const lemon = 'lemon';
            const list = new List_7.List();
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            this.expectNumber(list.getSize()).equals(3);
            this.expect(list.contains(apple));
            this.expect(list.contains(banana));
            this.expect(list.contains(cerish));
            this.expectNot(list.contains(lemon));
        }
        testCase_iterator_whenListContains3Elements() {
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            const lemon = 'lemon';
            const list = new List_7.List();
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            var i = 1;
            for (const e of list) {
                switch (i) {
                    case 1:
                        this.expectString(e).isSameAs(apple);
                        break;
                    case 2:
                        this.expectString(e).isSameAs(banana);
                        break;
                    case 3:
                        this.expectString(e).isSameAs(cerish);
                        break;
                    default:
                        this.expectNotThisCase();
                }
                i++;
            }
        }
        testCase_iterator_whenListIsEmpty() {
            const list = new List_7.List();
            var foundElement = false;
            for (const e of list) {
                foundElement = true;
            }
            this.expectNot(foundElement);
        }
        testCase_removeFirst() {
            const apple = 'apple';
            const banana = 'banana';
            const cerish = 'cerish';
            const list = new List_7.List();
            list.addAtEnd(apple);
            list.addAtEnd(banana);
            list.addAtEnd(cerish);
            this.expectNumber(list.getSize()).equals(3);
            this.expectString(list.getRefFirst()).isSameAs(apple);
            list.removeFirst();
            this.expectNumber(list.getSize()).equals(2);
            this.expectString(list.getRefFirst()).isSameAs(banana);
        }
    }
    exports.ListTest = ListTest;
});
define("CoreTest/ContainerTest/SingleContainerTest", ["require", "exports", "Core/Container/SingleContainer", "Core/Test/Test"], function (require, exports, SingleContainer_1, Test_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class SingleContainerTest extends Test_3.Test {
        testCase_createWith_whenTheGivenElementIsNull() {
            this.expectFunction(() => SingleContainer_1.SingleContainer.withElement(null))
                .throwsError()
                .withMessage('The given element is null.');
        }
        testCase_createWith_whenTheGivenElementIsUndefined() {
            this.expectFunction(() => SingleContainer_1.SingleContainer.withElement(undefined))
                .throwsError()
                .withMessage('The given element is undefined.');
        }
    }
    exports.SingleContainerTest = SingleContainerTest;
});
define("CoreTest/ContainerTest/ContainerTestPool", ["require", "exports", "CoreTest/ContainerTest/ListTest", "CoreTest/ContainerTest/SingleContainerTest", "Core/Test/TestPool"], function (require, exports, ListTest_1, SingleContainerTest_1, TestPool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ContainerTestPool extends TestPool_2.TestPool {
        constructor() {
            super();
            this.addTest(new ListTest_1.ListTest());
            this.addTest(new SingleContainerTest_1.SingleContainerTest());
        }
    }
    exports.ContainerTestPool = ContainerTestPool;
});
define("CoreTest/NodeTest/NodeTest", ["require", "exports", "Core/Node/Node", "Core/Test/Test"], function (require, exports, Node_3, Test_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NodeTest extends Test_4.Test {
        testCase_addAttribute() {
            const node = new Node_3.Node();
            const attribute1 = new Node_3.Node();
            const attribute2 = new Node_3.Node();
            const attribtue3 = new Node_3.Node();
            this.expectNot(node.containsAttributes());
            node.addAttribute(attribute1);
            node.addAttribute(attribute2);
            node.addAttribute(attribtue3);
            this.expectNumber(node.getAttributeCount()).equals(3);
        }
        testCase_set() {
            const node = new Node_3.Node();
            node.set('');
            this.expectString(node.toString()).equals('');
        }
        testCase_set2() {
            const node = new Node_3.Node();
            node.set('A');
            this.expectString(node.toString()).equals('A');
        }
        testCase_set3() {
            const node = new Node_3.Node();
            node.set('A(B)');
            this.expectString(node.toString()).equals('A(B)');
        }
        testCase_set4() {
            const node = new Node_3.Node();
            node.set('A(B1,B2,B3)');
            this.expectString(node.toString()).equals('A(B1,B2,B3)');
        }
        testCase_set5() {
            const node = new Node_3.Node();
            node.set('A(B(C))');
            this.expectString(node.toString()).equals('A(B(C))');
        }
        testCase_set6() {
            const node = new Node_3.Node();
            node.set('A(B1(C1),B2(C2))');
            this.expectString(node.toString()).equals('A(B1(C1),B2(C2))');
        }
        testCase_set7() {
            const node = new Node_3.Node();
            node.set('A(B1(C1,C2),B2(C3,C4))');
            this.expectString(node.toString()).equals('A(B1(C1,C2),B2(C3,C4))');
        }
        testCase_setHeader() {
            const node = new Node_3.Node();
            this.expectNot(node.hasHeader());
            node.setHeader('H');
            this.expectString(node.getHeader()).equals('H');
        }
    }
    exports.NodeTest = NodeTest;
});
define("CoreTest/NodeTest/NodeTestPool", ["require", "exports", "CoreTest/NodeTest/NodeTest", "Core/Test/TestPool"], function (require, exports, NodeTest_1, TestPool_3) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NodeTestPool extends TestPool_3.TestPool {
        constructor() {
            super();
            this.addTest(new NodeTest_1.NodeTest());
        }
    }
    exports.NodeTestPool = NodeTestPool;
});
define("CoreTest/CoreTestPool", ["require", "exports", "CoreTest/ChainedNodeTest/ChainedNodeTestPool", "CoreTest/ContainerTest/ContainerTestPool", "CoreTest/NodeTest/NodeTestPool", "Core/Test/TestPool"], function (require, exports, ChainedNodeTestPool_1, ContainerTestPool_1, NodeTestPool_1, TestPool_4) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class CoreTestPool extends TestPool_4.TestPool {
        constructor() {
            super();
            this.addTestPool(new ChainedNodeTestPool_1.ChainedNodeTestPool);
            this.addTestPool(new ContainerTestPool_1.ContainerTestPool());
            this.addTestPool(new NodeTestPool_1.NodeTestPool());
        }
    }
    exports.CoreTestPool = CoreTestPool;
});
define("CoreTest/Launcher", ["require", "exports", "CoreTest/CoreTestPool"], function (require, exports, CoreTestPool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new CoreTestPool_1.CoreTestPool().run();
});
define("CoreTest/ChainedNodeTest/Launcher", ["require", "exports", "CoreTest/ChainedNodeTest/ChainedNodeTestPool"], function (require, exports, ChainedNodeTestPool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new ChainedNodeTestPool_2.ChainedNodeTestPool().run();
});
define("CoreTest/ContainerTest/Launcher", ["require", "exports", "CoreTest/ContainerTest/ContainerTestPool"], function (require, exports, ContainerTestPool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new ContainerTestPool_2.ContainerTestPool().run();
});
define("CoreTest/NodeTest/Launcher", ["require", "exports", "CoreTest/NodeTest/NodeTestPool"], function (require, exports, NodeTestPool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new NodeTestPool_2.NodeTestPool().run();
});
define("Element/input/Key", ["require", "exports"], function (require, exports) {
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
        Key[Key["F1"] = 39] = "F1";
        Key[Key["F2"] = 40] = "F2";
        Key[Key["F3"] = 41] = "F3";
        Key[Key["F4"] = 42] = "F4";
        Key[Key["F5"] = 43] = "F5";
        Key[Key["F6"] = 44] = "F6";
        Key[Key["F7"] = 45] = "F7";
        Key[Key["F8"] = 46] = "F8";
        Key[Key["F9"] = 47] = "F9";
        Key[Key["F10"] = 48] = "F10";
        Key[Key["F11"] = 49] = "F11";
        Key[Key["F12"] = 50] = "F12";
        Key[Key["ARROW_UP"] = 51] = "ARROW_UP";
        Key[Key["ARROW_DOWN"] = 52] = "ARROW_DOWN";
        Key[Key["ARROW_LEFT"] = 53] = "ARROW_LEFT";
        Key[Key["ARROW_RIGHT"] = 54] = "ARROW_RIGHT";
        Key[Key["ALTERNATIVE"] = 55] = "ALTERNATIVE";
        Key[Key["BACKSPACE"] = 56] = "BACKSPACE";
        Key[Key["BREAK"] = 57] = "BREAK";
        Key[Key["CAPS_LOOK"] = 58] = "CAPS_LOOK";
        Key[Key["COMMA"] = 59] = "COMMA";
        Key[Key["CONTROL"] = 60] = "CONTROL";
        Key[Key["DELETE"] = 61] = "DELETE";
        Key[Key["DOLLAR_SYMBOL"] = 62] = "DOLLAR_SYMBOL";
        Key[Key["DOT"] = 63] = "DOT";
        Key[Key["END"] = 64] = "END";
        Key[Key["ENTER"] = 65] = "ENTER";
        Key[Key["ESCAPE"] = 66] = "ESCAPE";
        Key[Key["EXCLAMATION_MARK"] = 67] = "EXCLAMATION_MARK";
        Key[Key["GRADE_SYMBOL"] = 68] = "GRADE_SYMBOL";
        Key[Key["GRAVIS"] = 69] = "GRAVIS";
        Key[Key["HOME"] = 70] = "HOME";
        Key[Key["HYPHEN"] = 71] = "HYPHEN";
        Key[Key["INSERT"] = 72] = "INSERT";
        Key[Key["MENU"] = 73] = "MENU";
        Key[Key["NUMBER_LOCK"] = 74] = "NUMBER_LOCK";
        Key[Key["PAGE_DOWN"] = 75] = "PAGE_DOWN";
        Key[Key["PAGE_UP"] = 76] = "PAGE_UP";
        Key[Key["PRINT_SCREEN"] = 77] = "PRINT_SCREEN";
        Key[Key["QUESTION_MARK"] = 78] = "QUESTION_MARK";
        Key[Key["RELATIONS"] = 79] = "RELATIONS";
        Key[Key["SCROLL_LOCK"] = 80] = "SCROLL_LOCK";
        Key[Key["SHIFT"] = 81] = "SHIFT";
        Key[Key["SPACE"] = 82] = "SPACE";
        Key[Key["TABULATOR"] = 83] = "TABULATOR";
        Key[Key["WINDOWS"] = 84] = "WINDOWS";
    })(Key = exports.Key || (exports.Key = {}));
});
define("Element/BaseGUI_API/IEventTaker", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
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
            return new Color(Number.parseInt(string.substr(2, 4)), Number.parseInt(string.substring(4, 6)), Number.parseInt(string.substring(6, 8)));
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
define("Element/PainterAPI/IPainter", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
});
define("Element/BrowserGUI/BrowserGUIPainter", ["require", "exports", "Element/Color/Color", "Core/Container/SingleContainer", "Element/TextFormat/TextFormat"], function (require, exports, Color_2, SingleContainer_2, TextFormat_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BrowserGUIPainter {
        constructor(xPosition, yPosition, canvasRenderingContext, parentPainterContainer) {
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
            if (canvasRenderingContext === null) {
                throw new Error('The given canvasRenderingContext is null.');
            }
            if (canvasRenderingContext === undefined) {
                throw new Error('The given canvasRenderingContext is undefined.');
            }
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.canvasRenderingContext = canvasRenderingContext;
            this.parentPainter = parentPainterContainer.isEmpty() ? undefined : parentPainterContainer.getRefElement();
        }
        static createPainter(canvasRenderingContext) {
            return new BrowserGUIPainter(0, 0, canvasRenderingContext, SingleContainer_2.SingleContainer.EMPTY_CONTAINER);
        }
        createPainter() {
            return new BrowserGUIPainter(this.xPosition, this.yPosition, this.canvasRenderingContext, SingleContainer_2.SingleContainer.withElement(this));
        }
        createPainterWithTranslation(xTranslation, yTranslation) {
            return new BrowserGUIPainter(this.xPosition + xTranslation, this.yPosition + yTranslation, this.canvasRenderingContext, SingleContainer_2.SingleContainer.withElement(this));
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
            this.applyPosition();
            this.canvasRenderingContext.fillRect(0, 0, width, height);
        }
        paintFilledRectangleAtPosition(xPosition, yPosition, width, height) {
            this.applyPositionAndTranslation(xPosition, yPosition);
            this.canvasRenderingContext.fillRect(0, 0, width, height);
        }
        paintText(text) {
            this.paintTextWithTextFormat(text, BrowserGUIPainter.DEFAULT_TEXT_FORMAT);
        }
        paintTextWithTextFormat(text, textFormat) {
            this.applyPosition();
            this.canvasRenderingContext.font = textFormat.getTextSize() + 'px Arial';
            this.canvasRenderingContext.fillStyle = textFormat.getTextColor().getHTMLCode();
            this.canvasRenderingContext.fillText(text, 0, 0);
        }
        paintTextWithTextFormatAndMaxLength(text, textFormat, maxLength) {
            this.paintTextWithTextFormat(text, BrowserGUIPainter.DEFAULT_TEXT_FORMAT);
        }
        setColor(color) {
            this.canvasRenderingContext.fillStyle = color.getHTMLCode();
        }
        translate(xTranslation, yTranslation) {
            this.xPosition += xTranslation;
            this.yPosition += yTranslation;
        }
        applyPosition() {
            this.canvasRenderingContext.setTransform(1, 0, 0, 1, 0, 0);
            this.canvasRenderingContext.translate(this.getXPositionOnViewArea(), this.getYPositionOnViewArea());
        }
        applyPositionAndTranslation(xTranslation, yTranslation) {
            this.canvasRenderingContext.setTransform(1, 0, 0, 1, 0, 0);
            this.canvasRenderingContext.translate(this.getXPositionOnViewArea() + xTranslation, this.getYPositionOnViewArea() + yTranslation);
        }
    }
    BrowserGUIPainter.DEFAULT_TEXT_SIZE = 10;
    BrowserGUIPainter.DEFAULT_TEXT_COLOR = Color_2.Color.BLACK;
    BrowserGUIPainter.DEFAULT_TEXT_FORMAT = new TextFormat_1.TextFormat(BrowserGUIPainter.DEFAULT_TEXT_SIZE, BrowserGUIPainter.DEFAULT_TEXT_COLOR);
    exports.BrowserGUIPainter = BrowserGUIPainter;
});
define("Element/BrowserGUI/BrowserGUIProtocol", ["require", "exports"], function (require, exports) {
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
define("Element/BrowserGUI/PaintProcess", ["require", "exports", "Core/Container/List"], function (require, exports, List_8) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class PaintProcess {
        constructor(initialPainter, paintCommands) {
            this.painters = new List_8.List();
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
define("Element/BrowserGUI/BrowserGUI", ["require", "exports", "Element/BrowserGUI/BrowserGUIPainter", "Element/BrowserGUI/BrowserGUIProtocol", "Element/Color/Color", "Core/Container/List", "Element/BrowserGUI/PaintProcess", "Element/TextFormat/TextFormat"], function (require, exports, BrowserGUIPainter_1, BrowserGUIProtocol_1, Color_3, List_9, PaintProcess_1, TextFormat_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class BrowserGUI {
        constructor(window, eventTaker) {
            this.onResize = () => {
                this.canvas.width = this.window.innerWidth;
                this.canvas.height = this.window.innerHeight;
                this.eventTaker.noteResize(this.canvas.width, this.canvas.height);
            };
            if (window === null) {
                throw new Error('The given window is null.');
            }
            if (window === undefined) {
                throw new Error('The given window is undefined.');
            }
            if (eventTaker === null) {
                throw new Error('The given eventTaker is null.');
            }
            if (eventTaker === undefined) {
                throw new Error('The given eventTaker is undefined.');
            }
            this.eventTaker = eventTaker;
            this.paintCommands = new List_9.List();
            this.window = window;
            this.canvas = window.document.createElement('canvas');
            this.window.document.body.appendChild(this.canvas);
            this.canvas.width = 1000;
            this.canvas.height = 1000;
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
        refresh() {
            const renderContext = this.canvas.getContext('2d');
            renderContext.clearRect(0, 0, this.getViewAreaWidth(), this.getViewAreaHeight());
            const painter = BrowserGUIPainter_1.BrowserGUIPainter.createPainter(renderContext);
            this.paintBackground(painter);
            new PaintProcess_1.PaintProcess(painter, this.paintCommands);
            painter.setColor(Color_3.Color.BLACK);
            painter.paintFilledRectangle(50, 50);
        }
        reset() {
            this.setTitle(BrowserGUI.DEFAULT_TITLE);
        }
        setPaintCommands(paintCommands) {
            this.paintCommands.refill(paintCommands);
            this.refresh();
        }
        setTextualPaintCommands(textualPaintCommands) {
            console.log('The current BrowserGUI sets the given textualPaintCommands ' + textualPaintCommands);
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
                case BrowserGUIProtocol_1.BrowserGUIProtocol.CREATE_PAINTER_HEADER:
                    return this.createCreatePainterCommand(painterIndex, textualPaintCommand);
                case BrowserGUIProtocol_1.BrowserGUIProtocol.PAINT_FILLED_RECTANGLE_HEADER:
                    return this.createPaintFilledRectangleCommand(painterIndex, textualPaintCommand);
                case BrowserGUIProtocol_1.BrowserGUIProtocol.PAINT_TEXT_HEADER:
                    return this.createPaintTextCommand(painterIndex, textualPaintCommand);
                case BrowserGUIProtocol_1.BrowserGUIProtocol.SET_COLOR_HEADER:
                    return this.createSetColorCommand(painterIndex, textualPaintCommand);
                case BrowserGUIProtocol_1.BrowserGUIProtocol.TRANSLATE_HEADER:
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
    BrowserGUI.BACKGROUND_COLOR = new Color_3.Color(127, 127, 127);
    exports.BrowserGUI = BrowserGUI;
});
define("ElementTest/ColorTest/ColorTest", ["require", "exports", "Element/Color/Color", "Core/Test/Test"], function (require, exports, Color_4, Test_5) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ColorTest extends Test_5.Test {
        testCase_getHTMLCode() {
            const color = new Color_4.Color(0, 0, 0);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#000000');
        }
        testCase_getHTMLCode_2() {
            const color = new Color_4.Color(255, 255, 255);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#FFFFFF');
        }
        testCase_getHTMLCode_3() {
            const color = new Color_4.Color(1, 1, 1);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#010101');
        }
        testCase_getHTMLCode_4() {
            const color = new Color_4.Color(0, 64, 128);
            const HTMLCode = color.getHTMLCode();
            this.expectString(HTMLCode).equals('#004080');
        }
    }
    exports.ColorTest = ColorTest;
});
define("ElementTest/ColorTest/ColorTestPool", ["require", "exports", "ElementTest/ColorTest/ColorTest", "Core/Test/TestPool"], function (require, exports, ColorTest_1, TestPool_5) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ColorTestPool extends TestPool_5.TestPool {
        constructor() {
            super();
            this.addTest(new ColorTest_1.ColorTest());
        }
    }
    exports.ColorTestPool = ColorTestPool;
});
define("ElementTest/ElementTestPool", ["require", "exports", "ElementTest/ColorTest/ColorTestPool", "Core/Test/TestPool"], function (require, exports, ColorTestPool_1, TestPool_6) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class ElementTestPool extends TestPool_6.TestPool {
        constructor() {
            super();
            this.addTestPool(new ColorTestPool_1.ColorTestPool);
        }
    }
    exports.ElementTestPool = ElementTestPool;
});
define("ElementTest/ColorTest/Launcher", ["require", "exports", "ElementTest/ColorTest/ColorTestPool"], function (require, exports, ColorTestPool_2) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new ColorTestPool_2.ColorTestPool().run();
});
define("NolixTest/NolixTestPool", ["require", "exports", "CoreTest/CoreTestPool", "ElementTest/ElementTestPool", "Core/Test/TestPool"], function (require, exports, CoreTestPool_2, ElementTestPool_1, TestPool_7) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class NolixTestPool extends TestPool_7.TestPool {
        constructor() {
            super();
            this.addTestPool(new CoreTestPool_2.CoreTestPool());
            this.addTestPool(new ElementTestPool_1.ElementTestPool());
        }
    }
    exports.NolixTestPool = NolixTestPool;
});
define("NolixTest/Launcher", ["require", "exports", "NolixTest/NolixTestPool"], function (require, exports, NolixTestPool_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    new NolixTestPool_1.NolixTestPool().run();
});
define("System/FrontBrowserGUIClient/FrontBrowserGUIClientProtocol", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontBrowserGUIClientProtocol {
    }
    FrontBrowserGUIClientProtocol.GUI_HEADER = 'GUI';
    FrontBrowserGUIClientProtocol.NOTE_RESIZE_HEADER = "NoteResize";
    FrontBrowserGUIClientProtocol.SET_PAINT_COMMANDS_HEADER = 'SetPaintCommands';
    FrontBrowserGUIClientProtocol.SET_TITLE_HEADER = "SetTitle";
    exports.FrontBrowserGUIClientProtocol = FrontBrowserGUIClientProtocol;
});
define("System/FrontBrowserGUIClient/GUIHandler", ["require", "exports", "Element/BrowserGUI/BrowserGUI", "System/FrontBrowserGUIClient/FrontBrowserGUIClientProtocol"], function (require, exports, BrowserGUI_1, FrontBrowserGUIClientProtocol_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class GUIHandler {
        constructor(window, eventTaker) {
            this.mGUI = new BrowserGUI_1.BrowserGUI(window, eventTaker);
        }
        canRunCommand(command) {
            return (command.hasHeader() && this.canRunCommandOfType(command.getHeader()));
        }
        canRunCommandOfType(type) {
            switch (type) {
                case FrontBrowserGUIClientProtocol_1.FrontBrowserGUIClientProtocol.GUI_HEADER:
                    return true;
                default:
                    return false;
            }
        }
        runGUICommand(pGUICommand) {
            console.log('The current GUIHandler runs the given pGUICommand: ' + pGUICommand);
            switch (pGUICommand.getHeader()) {
                case FrontBrowserGUIClientProtocol_1.FrontBrowserGUIClientProtocol.SET_TITLE_HEADER:
                    this.mGUI.setTitle(pGUICommand.getOneAttributeAsString());
                    break;
                case FrontBrowserGUIClientProtocol_1.FrontBrowserGUIClientProtocol.SET_PAINT_COMMANDS_HEADER:
                    this.setPaintCommands(pGUICommand.getAttributes());
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
define("System/FrontBrowserGUIClient/ReceiverController", ["require", "exports"], function (require, exports) {
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
define("System/FrontBrowserGUIClient/FrontBrowserGUIClient", ["require", "exports", "Core/ChainedNode/ChainedNode", "System/FrontBrowserGUIClient/FrontBrowserGUIClientProtocol", "System/FrontBrowserGUIClient/GUIHandler", "Core/EndPoint5/NetEndPoint5", "Core/Node/Node", "System/FrontBrowserGUIClient/ReceiverController", "Core/Container/SingleContainer", "Core/Container/List", "System/FrontBrowserGUIClient/EventTaker"], function (require, exports, ChainedNode_3, FrontBrowserGUIClientProtocol_2, GUIHandler_1, NetEndPoint5_1, Node_4, ReceiverController_1, SingleContainer_3, List_10, EventTaker_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class FrontBrowserGUIClient {
        static withIpAndNumberAndWindow(ip, port, window) {
            return new FrontBrowserGUIClient(ip, port, SingleContainer_3.SingleContainer.EMPTY_CONTAINER, window);
        }
        constructor(ip, port, optionalTarget, window) {
            this.endPoint = new NetEndPoint5_1.NetEndPoint5(ip, port, optionalTarget);
            this.endPoint.setReceiverController(new ReceiverController_1.ReceiverController(c => this.run(c), r => this.getData(r)));
            this.mGUIHandler = new GUIHandler_1.GUIHandler(window, new EventTaker_1.EventTaker(this));
        }
        getGUIType() {
            return 'CanvasGUI';
        }
        noteResizeOnCounterpart(viewAreaWidth, viewAreaHeight) {
            const attributes = new List_10.List();
            attributes.addAtEnd(Node_4.Node.withNumberHeader(viewAreaWidth));
            attributes.addAtEnd(Node_4.Node.withNumberHeader(viewAreaHeight));
            this.endPoint.run(ChainedNode_3.ChainedNode.withHeaderAndAttributesFromNodes(FrontBrowserGUIClientProtocol_2.FrontBrowserGUIClientProtocol.NOTE_RESIZE_HEADER, attributes));
        }
        getData(request) {
            console.log('FrontBrowserGUIClient has received the request: ' + request.toString());
            switch (request.getHeader()) {
                case 'GUI_Type':
                    return Node_4.Node.withHeader(this.getGUIType());
                default:
                    throw new Error('The given request is not valid:' + request.toString());
            }
        }
        run(command) {
            console.log('FrontBrowserGUIClient runs the command: ' + command.toString());
            switch (command.getHeader()) {
                case FrontBrowserGUIClientProtocol_2.FrontBrowserGUIClientProtocol.GUI_HEADER:
                    this.mGUIHandler.runGUICommand(command.getNextNode());
                    break;
                default:
                    throw new Error('The given command is not valid.');
            }
        }
    }
    exports.FrontBrowserGUIClient = FrontBrowserGUIClient;
});
define("System/FrontBrowserGUIClient/EventTaker", ["require", "exports"], function (require, exports) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    class EventTaker {
        constructor(parentFrontBrowserGUIClient) {
            if (parentFrontBrowserGUIClient === null) {
                throw new Error('The given parentFrontBrowserGUIClient is null.');
            }
            if (parentFrontBrowserGUIClient === undefined) {
                throw new Error('The given parentFrontBrowserGUIClient is undefined.');
            }
            this.parentFrontBrowserGUIClient = parentFrontBrowserGUIClient;
        }
        noteKeyPress(key) {
            throw new Error("Method not implemented.");
        }
        noteKeyRelease(key) {
            throw new Error("Method not implemented.");
        }
        noteKeyTyping(key) {
            throw new Error("Method not implemented.");
        }
        noteLeftMouseButtonClick() {
            throw new Error("Method not implemented.");
        }
        noteLeftMouseButtonPress() {
            throw new Error("Method not implemented.");
        }
        noteLeftMouseButtonRelease() {
            throw new Error("Method not implemented.");
        }
        noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea) {
            throw new Error("Method not implemented.");
        }
        noteMouseWheelClick() {
            throw new Error("Method not implemented.");
        }
        noteMouseWheelPress() {
            throw new Error("Method not implemented.");
        }
        noteMouseWheelRelease() {
            throw new Error("Method not implemented.");
        }
        noteResize(viewAreaWidth, viewAreaHeight) {
            this.parentFrontBrowserGUIClient.noteResizeOnCounterpart(viewAreaWidth, viewAreaHeight);
        }
        noteRightMouseButtonClick() {
            throw new Error("Method not implemented.");
        }
        noteRightMouseButtonPress() {
            throw new Error("Method not implemented.");
        }
        noteRightMouseButtonRelease() {
            throw new Error("Method not implemented.");
        }
    }
    exports.EventTaker = EventTaker;
});

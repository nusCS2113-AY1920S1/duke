package com.nwjbrandon.duke.constants;

public enum TaskNames {
    LIST {
        public String toString() {
            return "list";
        }
    },
    DONE {
        public String toString() {
            return "done";
        }
    },
    TODO {
        public String toString() {
            return "todo";
        }
    },
    EVENT {
        public String toString() {
            return "event";
        }
    },
    DEADLINE {
        public String toString() {
            return "deadline";
        }
    },
    DELETE {
        public String toString() {
            return "delete";
        }
    },
    FIND {
        public String toString() {
            return "find";
        }
    },
    BYE {
        public String toString() {
            return "bye";
        }
    };
}

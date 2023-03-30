package data;
public enum Semester {
    FIRST{
        @Override
        public String toString() {
            return "первый";
        }
    },
    THIRD{
        @Override
        public String toString() {
            return "третий";
        }
    },
    EIGHTH{
        @Override
        public String toString() {
            return "восьмой";
        }
    };
}
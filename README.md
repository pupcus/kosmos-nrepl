# kosmos-nrepl

A system component to easily add a nrepl to your application

[![Clojars Project](https://img.shields.io/clojars/v/kosmos/kosmos-nrepl.svg)](https://clojars.org/kosmos/kosmos-nrepl)

## Usage

add something like this to your kosmos .edn files (the port can vary according to your needs):

```
:nrepl
{
:kosmos/init :kosmos.nrepl/NreplComponent
:port        5001
}
```

connect to your repl however you want (i.e. `M-x cider-connect`, `lein repl :connect 5001`)

## License

Kosmos is distributed under the [Eclipse Public License](http://opensource.org/licenses/eclipse-1.0.php), the same as Clojure.
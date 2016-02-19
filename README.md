# kosmos-nrepl

A system component to easily add a nrepl to your application

## Usage

add something like this to your kosmos .edn files:

```
:nrepl
{
:kosmos/type :kosmos.nrepl/NreplComponent
:port 5001 ;; or some other port number
}
```

connect to your repl however you want (i.e. `M-x cider-connect`, `lein repl :connect 5001`)

## License

Kosmos is distributed under the [Eclipse Public License](http://opensource.org/licenses/eclipse-1.0.php), the same as Clojure.
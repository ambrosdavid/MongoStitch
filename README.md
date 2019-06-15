## Project title
Database ad oggetti creato con MongoDB-Atlas, basato su una lista di cocktails e drink.

![ezgif.com-resize_4_](uploads/fde3621563456c07baf4687684407da3/ezgif.com-resize_4_.gif)
![mdb](uploads/0d548fef23caf538e759c796026db09a/mdb.png)
![ezgif.com-resize_3_](uploads/da9741e278c50d9c12bcdaec7124d775/ezgif.com-resize_3_.gif)

## Perchè Mongo
La decisione di lavorare con mongo è puramente soggettiva, a scopo autodidattico.
Tramite l'uso di mongo rappresentare la lisa di drink o cocktail risulta molto piu intuitiva e ne rappresenta nel modo piu diretto possibile gli oggetti reali. 
Non essendo un database relazionale, li oggetti possono avere tutti un corpo diverso tra di loro, questo permette una gestione molto meno restrittiva della lista di drink, che quindi non devono avere uno schema preciso.

Il corpo puo essere complicato e il modello relazionale risultirebbe poco adatto a rappresentare informazioni complesse. Mongo evita di modellare le entità per valore, risparmiando colonne/tabelle aggiuntive, soppratutto in grandi insiemi di dati, con la seguente neccessita di operazioni complesse per la neccessita di trattare tali strutture. Mongo permette, a differenza di sql, di creare strutture annidate come array e insieme,e spratutto di rappresentare oggetti reali nel modo piu diretto possibile. 
La possibilità di modellare strutture complesse conduce alla costruzione e alla gestione di una architettura dati molto vicina allo schema concettuale del progetto; le fasi di sviluppo e di gestione si uniscono concentrando lo studio su un unico modello. Si riducono cosi i problemi di comunicabilità derivanti dal passaggio dall'analisi, alla progettazione e alla programmazione.

## Motivation
Progetto creato per il conseguente collegamento ad applicazione android e per accedere alle proprie api personalizate (create con stitch) della collezione di cocktails, quindi disponendo di una serie di interessanti azioni con cui poter lavorare(CRUD).
Ho deciso di lavorare con cocktails e drink, inserendone la lista nel database e popolando quindi le colezioni con `NODEJS`

## Descrizione
Servizio realizzato: server cloud Stitch (MongoDB), tale servizio è CRUD (`GET` per ricevere i dati, `POST` per inviare, ma anche aggiornare e cancellare i dati, `PUT` per aggiornamento e `DELETE` per cancellazione).

Database ad oggetti scelto: MongoDB. Il database ad oggetti possiede almeno una collezione dove i documenti contengono come valore sia array che sotto-documenti.

## Esposizione database
Nel database CocktailsDB è presente una collezione: Cocktails.

Cocktails specifica la descrizione per ogni Cocktail, indicandone il nome, specifiche, ingredienti, categoria, tasso alcolico ecc.
Oltre ai Cocktails è possibile implementare in futuro un ulteriore collezione con un altra categoria(ES. Cocktails, SoftDrinks, Analcolici)

![Cocktails](uploads/12b4eb717efb4b99007f268d9dd0df40/Cocktails.PNG)

## Esposizione servizi Stitch 
<b>GET</b>
Il primo servizio che fa parte del `CRUD` è il get, è un servizio di tipo HTTP(come tutti gli altri), e all'interno presenta un webhook grazie al quale si possono richiedere l'intera lista dei Cocktails, oppure tramite parametri richiederne uno in particolare a seconda del nome

```
exports = function(payload) {

    var ris; 
    const {nomeDrink}=payload.query;
    
    if(nomeDrink!=undefined){
       ris = context.services.get("mongodb-atlas").db("CocktailsDB").collection("Cocktails").find({strDrink:nomeDrink});
    }else{
      ris= context.services.get("mongodb-atlas").db("CocktailsDB").collection("Cocktails").find();
    }
    return ris.toArray();
  };
```

<b>Insert</b>
Insert è un servizion `POST` che prende come parametro un oggetto JSON o query, e lo inserisce all'interno della collezione Cocktails usando insertOne();
```
exports = function(payload) {
  const drink = EJSON.parse(payload.body.text());
  const drinks =context.services.get("mongodb-atlas").db("CocktailsDB").collection("Cocktails");
  drinks.insertOne(drink);
  drinks.updateOne(drink,  {$currentDate: { dateModified: true }}); 
};
```

<b>Delete</b>
Delete è un servizio che permette la cancellazione di un oggetto presente nel database, prende come parametro un oggetto json o una query. Si puo usare deleteOne() oppure deleteMany(), per cancellare rispettivamente oggetti singoli o multipli
```
exports = function(payload) {
  const drink = EJSON.parse(payload.body.text());
  const collection =context.services.get("mongodb-atlas").db("CocktailsDB").collection("Cocktails");
  collection.deleteMany(drink);
};
```

<b>Update</b>
Il servizio update, realizzato tramite un `POST`. permette di modificare un oggetto presente nel database.
Per farlo bisogna usare updateOne(), che prende come parametro l'oggetto iniziale e quello finale
![update](uploads/f349892eeb4d4f60a7e3764a2d4391a9/update.PNG)
```
  Cocktails.updateOne(Cocktail.last, Cocktail.new);
```
 oppure l'oggetto iniziale e una query
```
 return collection
     .updateOne({"username": uname}, {"$set": {"zip": new_zip}})
```
Esempio:
```
exports = function(payload) {
  const Cocktail = EJSON.parse(payload.body.text());
  const Cocktails =context.services.get("mongodb-atlas").db("CocktailsDB").collection("Cocktails");
  Cocktails.updateOne(Cocktail.last, Cocktail.new);
  Cocktails.updateOne(Cocktail.new,  { $currentDate: { dateModified: true }}, {"$set": {"strDrink": "spritzPotentissimo"}}); 
};
```

## Appunti/Informazioni utili 
Stitch:server, mongoDB : database , Atlas : macchina che hosta il database.
Per l'accesso ai dati e richieste HTTP è stato usato Eclipse

<b>Differenza tra delete e post</b>

<b>[</b>DELETE is idempotent and POST is not.
In simple terms if you hit DELETE endpoint multiple times it will have effect same as hitting it once.
For example : If you delete something/some file on server by hitting DELETE endpoint multiple times it will still have effect of deleting that file only once. Because you can delete the one file only once even if you hit endpoint multiple time <- this explains the meaning of word “Idempotent”.
But in case of POST, POST endpoints are supposed to change state of server or data on the server .
For example : Usually POST endpoints are not supposed to be hit twice. Remember when you try to refresh payment page browser give warning that you are about to resubmit the request.
which means if you hit that end point again you will end up buying all the items in your shopping cart twice.<b>]</b>



MIT © [David]()

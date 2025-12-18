# Matriz con Listas Ortogonales (Linked Matrix)

Este proyecto construye una matriz **size x size** utilizando listas ortogonales,
es decir, cada nodo se enlaza con sus vecinos en cuatro direcciones:

- **up** (arriba)
- **down** (abajo)
- **left** (izquierda)
- **right** (derecha)

La construcción se realiza en dos fases:

1. **Crear la primera columna** (las filas "base") usando punteros **down / up**.
2. **Crear el resto de columnas** hacia la derecha, enlazando tanto horizontal como verticalmente.

--------------------------------------------------

## Estructura del Nodo

Cada **Node** mantiene referencias a sus cuatro vecinos y un valor (por ejemplo **mark**):

- Node up
- Node down
- Node left
- Node right
- char mark

El puntero **head** representa la esquina superior izquierda de la matriz (**[0][0]**).

--------------------------------------------------

## Fase 1: Creación de filas
(en realidad, la primera columna)

### Objetivo

Construir la columna 0 completa: **size nodos** conectados verticalmente.

### Idea

Partimos desde **head** y vamos creando nodos hacia abajo:

- El nodo actual (**temp**) crea un nuevo nodo debajo.
- Se enlaza en ambos sentidos:
  - **temp.down = newNode**
  - **newNode.up = temp**
- Se avanza **temp** al nuevo nodo y se repite el proceso.

De esta forma se obtiene una columna vertical que luego servirá como base para crear
las filas de la matriz.

### Código

```java
private void createRows() {
    Node temp = head;
    for (int i = 1; i < this.size; i++) {
        Node newNode = new Node(temp, null, null, null, '7');
        temp.setDown(newNode);
        newNode.setUp(temp);
        temp = newNode;
    }
}


## Resultado de esta fase

Al finalizar, existe una cadena vertical de **size nodos**:

head
  |
 down
  |
 down
  |
 ...

Cada uno de estos nodos representa el inicio de una fila de la matriz.

--------------------------------------------------

## Fase 2: Creación de columnas

### Objetivo

Construir las columnas restantes (**1 a size - 1**) hacia la derecha, enlazándolas correctamente con la columna inicial y entre sí.

--------------------------------------------------

## Idea general

Para crear columnas de forma ordenada se utiliza un **arreglo auxiliar de punteros**,
donde cada posición representa el último nodo creado en una fila específica.

- El arreglo **nodes[]** mantiene el nodo actual de cada fila.
- Inicialmente, **nodes[i]** apunta a los nodos de la primera columna
  (creados en **createRows**).
- Cada vez que se crea una nueva columna:
  - Se crean todos los nodos de esa columna.
  - Se enlazan verticalmente.
  - Se enlazan horizontalmente con **nodes[]**.
  - Se actualiza **nodes[]** para avanzar hacia la derecha.

Este enfoque evita referencias a nodos inexistentes y mantiene la matriz consistente.

--------------------------------------------------

## Algoritmo paso a paso

Para cada nueva columna:

1. Crear un arreglo **newNodes[]** de tamaño **size**, donde cada posición
   representa un nodo de la nueva columna.

2. Enlazar verticalmente los nodos del arreglo:
   - **newNodes[i-1].down = newNodes[i]**
   - **newNodes[i].up = newNodes[i-1]**

3. Enlazar horizontalmente cada nodo nuevo con su correspondiente nodo en **nodes[]**:
   - **nodes[i].right = newNodes[i]**
   - **newNodes[i].left = nodes[i]**

4. Actualizar:
   - **nodes[i] = newNodes[i]**

Este proceso se repite **size - 1** veces, ya que la primera columna ya existe.

--------------------------------------------------

### Código

```java
private void createColumns() {
    Node[] nodes = new Node[this.size];

    // Inicializar el arreglo con la primera columna
    Node temp = head;
    for (int i = 0; i < this.size; i++) {
        nodes[i] = temp;
        temp = temp.getDown();
    }

    // Crear las columnas restantes
    for (int col = 1; col < this.size; col++) {
        Node[] newNodes = new Node[this.size];

        // Crear los nodos de la nueva columna
        for (int i = 0; i < this.size; i++) {
            newNodes[i] = new Node(null, null, null, null, '8');
        }

        // Enlazar verticalmente la nueva columna
        for (int i = 1; i < this.size; i++) {
            newNodes[i - 1].setDown(newNodes[i]);
            newNodes[i].setUp(newNodes[i - 1]);
        }

        // Enlazar horizontalmente y avanzar los punteros de fila
        for (int i = 0; i < this.size; i++) {
            nodes[i].setRight(newNodes[i]);
            newNodes[i].setLeft(nodes[i]);
            nodes[i] = newNodes[i];
        }
    }
}




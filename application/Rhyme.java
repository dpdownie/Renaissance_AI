/**
 * Author: Drew Downie (downie@cs.wisc.edu)
 * Last Updated: 10/23/2020
 */
package application;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Drew Downie (2020)
 *
 */
public class Rhyme {
  
  private class Graph {
    List<GraphNode<Word>> vertexList; // List of all vertices
    int order; // number of vertices
    int size; // number of edges
    
    private Graph() {
      vertexList = null;
      order = 0;
      size = 0;
    }
    
    private Graph(HashMap<String, Word> alphabet) {
      vertexList = new LinkedList<GraphNode<Word>>();
      for (Word w : alphabet.values()) {
        this.addVertex(w);
      }
    }
    
    private class GraphNode<Word> {
      private Word word; // data of node
      private List<GraphNode<Word>> rhymeList; // adjacency list
      
      private GraphNode(Word word) {
        this.word = word;
        this.rhymeList = new LinkedList<GraphNode<Word>>();
      }
            
    }
    
    private GraphNode<Word> getVertex(Word v) {
      if (v == null || !this.contains(v)) {
        return null;
      }
      return this.vertexList.get(vertexList.indexOf(new GraphNode<Word>(v)));
    }
    
    private void addVertex(Word v) {
      if (v == null || this.contains(v)) {
        return;
      }
      GraphNode<Word> newV = new GraphNode<Word>(v);
      this.vertexList.add(newV);
    }
    
    private void removeVertex(Word v) {
      if (v == null || !this.contains(v)) {
        return;
      }
      this.vertexList.remove(new GraphNode<Word>(v));
    }
    
    private void addEdge(Word v1, Word v2) {
      if (!this.contains(v1) || !this.contains(v2)) {
        return;
      }
    }
    
    private boolean contains(Word v) {
      for (GraphNode<Word> n : vertexList) {
        if (n.word.equals(v)) {
          return true;
        }
      }
      return false;
    }
  }
}

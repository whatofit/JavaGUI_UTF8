package com.myblog.components.tree;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
public class TreeFrame extends JFrame {
    private JTree tree;
    public static void main(String[] args) {
        new TreeFrame();
    }
    public TreeFrame() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tree = new JTree();
        tree.addTreeExpansionListener(new TreeExpansionAction());
        setRootDir(new File("c:/windows"));
        this.add(new JScrollPane(tree));
        this.setVisible(true);
    }
    private void setRootDir(File dir) {
        tree.setModel(new DefaultTreeModel(createNode(dir)));
        MutableTreeNode rootNode = (MutableTreeNode) tree.getModel().getRoot();
        rootNode.setUserObject(dir);
        updateNode(rootNode);
        tree.updateUI();
    }
    private void updateNode(Object object) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) node.getChildAt(i);
            FileObject fileObject = (FileObject) node2.getUserObject();
            if (!fileObject.isUpdated()) {
                fileObject.setUpdated(true);
                File file = fileObject.getFile();
                if (file.isDirectory()) {
                    addSubFile(node2, file);
                }
            }
        }
    }
    public MutableTreeNode createNode(File dir) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileObject(dir));
        if (dir.isDirectory()) {
            addSubFile(node, dir);
        }
        return node;
    }
    private void addSubFile(DefaultMutableTreeNode node, File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                node.add(new DefaultMutableTreeNode(new FileObject(file)));
            }
        }
    }
    public class TreeExpansionAction implements TreeExpansionListener {
        public void treeExpanded(TreeExpansionEvent event) {
            updateNode(event.getPath().getLastPathComponent());
        }
        public void treeCollapsed(TreeExpansionEvent event) {
        }
    }
    public class FileObject {
        private File file;
        private boolean updated;
        public FileObject(File file) {
            this.file = file;
        }
        public File getFile() {
            return file;
        }
        public boolean isUpdated() {
            return updated;
        }
        public void setUpdated(boolean updated) {
            this.updated = updated;
        }
        public String toString() {
            return file.getName();
        }
    }
}
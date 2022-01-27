package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import beans.Article;
import beans.Categorie;
import beans.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/articles")
public class ArticleServlet extends HttpServlet {
	
	private List<Article> articles = new ArrayList<Article>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("utilisateurs") != null) {
			if(session.getAttribute("categories") != null) {
				req.setAttribute("categories", session.getAttribute("categories"));
				req.setAttribute("auteurs", session.getAttribute("utilisateurs"));
				req.setAttribute("articles", articles);
				this.getServletContext().getRequestDispatcher("/WEB-INF/article/create_article.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect("/exercice3/categories");
			}
		} else {
			resp.sendRedirect("/exercice3/utilisateurs");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titre = req.getParameter("titre");
		String description = req.getParameter("description");
		String contenu = req.getParameter("contenu");
		String categorie = req.getParameter("categorie");
		String auteur = req.getParameter("auteur");	
		String message; 
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<Categorie> categories = (List<Categorie>) session.getAttribute("categories");
		@SuppressWarnings("unchecked")
		List<Utilisateur> auteurs = (List<Utilisateur>) session.getAttribute("utilisateurs");
		
		if(categorie.trim().isEmpty()) {
			
		}
		if (titre.trim().isEmpty() || description.trim().isEmpty() || contenu.trim().isEmpty()) {
			message = "Merci de remplir tout les champs !";
			req.setAttribute("message", message);
		} else {
			Article article = new Article(); 
			article.setTitre(titre);
			article.setContenu(contenu);
			article.setDescription(description);
			categories.forEach(cat -> {
				if (cat.getTitre().equals(titre)) {
					article.setCategorie(cat);
				}
			});
			auteurs.forEach(a -> {
				if (a.getEmail().equals(auteur)) {
					article.setAuteur(a);
				}
			});
			articles.add(article);
		}
		req.setAttribute("categories", categories);
		req.setAttribute("auteurs", auteurs);
		req.setAttribute("articles", articles);
		this.getServletContext().getRequestDispatcher("/WEB-INF/article/create_article.jsp").forward(req, resp);
	}

}

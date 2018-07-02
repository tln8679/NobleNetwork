package beans;

public class Article {
	private String title;
	private String postContent;
	private String creator;
	
	public Article(String title, String postContent, String creator) {
		super();
		this.title = title;
		this.postContent = postContent;
		this.creator = creator;
	}
	
	public Article()
	{
		// no parameters
	}

	@Override
	public String toString() {
		return "Article [creator=" + creator + ", postContent=" + postContent
				+ ", title=" + title + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}

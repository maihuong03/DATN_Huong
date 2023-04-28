

package common;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFileFilter extends FileFilter {

	private String description;
	private String[] extensions;

	public CustomFileFilter() {
	}

	public CustomFileFilter(String[] extensions, String description) {
		this.extensions = extensions;
		this.description = description;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(
						path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public String[] getExtensions() {
		return extensions;
	}

	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

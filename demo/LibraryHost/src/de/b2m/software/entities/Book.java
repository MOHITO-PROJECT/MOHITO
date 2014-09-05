/** 
 * Copyright (c) 2012-2014 B2M Software AG
 *
 * Licensed under the EUPL V.1.1
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.b2m.software.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
@author sdubey (B2M Software AG)
 *
 */
@Entity
@Table(name = "Books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	@JoinColumn(name = "name_id")
	private Writer writer;
	
	@ManyToOne()
	@JoinColumn(name = "library_id")
	private Library library;
	
	@ManyToOne()
	@JoinColumn(name = "id")
	private Category category;

	@Id
	@Basic(optional = false)
	@Column(name = "title_id")
	/** Attribute title, this is also the primary key of the database. 
	 */
	private String title;

	@Basic(optional = true)
	@Column(name = "page_count")
	/** Attribute pages. 
	 */
	private Integer pages;

	@Basic(optional = true)
	@Column(name = "creation_time")
	@Temporal(TemporalType.TIMESTAMP)
	/** Attribute creation time. 
	 */
	private Date creationTime;

	@Basic(optional = true)
	@Column(name = "last_update_time")
	@Temporal(TemporalType.TIMESTAMP)
	/** Attribute last update time. 
	 */
	private Date lastUpdateTime;
	
	@Basic(optional = true)
	@Column(name = "last_modification_time")
	@Temporal(TemporalType.TIMESTAMP)
	/** Attribute last modification time.
	 */
	private Date lastModificationTime;
	
	
	/** Reference to the writer. 
	 */
	public Writer getWriter() {
		return this.writer;
	}

	/**
	 * Used to set the reference to the writer
	 * 
	 * @param writer reference to be set
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
		this.lastModificationTime = new Date();
	}

	
	/** Reference to the category. 
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Used to set the reference to the category
	 * 
	 * @param category reference to be set
	 */
	public void setCategory(Category category) {
		this.category = category;
		this.lastModificationTime = new Date();
	}

	/** Reference to the library. 
	 */
	public Library getLibrary() {
		return this.library;
	}

	/**
	 * Used to set the reference to the library
	 * 
	 * @param library reference to be set
	 */
	public void setLibrary(Library library) {
		this.library = library;
		this.lastModificationTime = new Date();
	}

	/**
	 * Default constructor
	 */
	public Book() {
		this.creationTime = new Date();
		this.lastModificationTime = new Date();
		this.lastUpdateTime = new Date();
	}

	/** Creates an instance
	 * @param title 	The title
	 * @param pages		The number of pages
	 */
	public Book(String title, Integer pages) {
		this.title = title;
		this.creationTime = new Date();
		this.lastModificationTime = new Date();
		this.lastUpdateTime = new Date();
		this.pages = pages;
	}

}

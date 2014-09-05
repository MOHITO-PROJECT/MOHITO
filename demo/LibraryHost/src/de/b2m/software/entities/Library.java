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
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
@author sdubey (B2M Software AG)
 *
 */
@Entity
@Table(name = "Libraries")
public class Library implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "library")
	private Collection<Writer> writers;
	
	@OneToMany(mappedBy = "library")
	private Collection<Book> books;

	@Id
	@Basic(optional = false)
	@Column(name = "library_id")
	/** Attribute id, this is also the primary key of the database. 
	 */
	private Integer id;

	@Basic(optional = false)
	@Column(name = "name")
	/** Attribute name. 
	 */
	private String name;

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
	
	
	/** Reference to the Writers (belonging to that category). 
	 */
	public Collection<Writer> getWriters() {
		return this.writers;
	}

	
	/**
	 * @return	returns the database Id
	 */
	public Integer getId() {
		return this.id;
	}

	
	/** Reference to the library (belonging to that category). 
	 */
	public Collection<Book> getBooks() {
		return this.books;
	}

	
	/** Creates an instance
	 * @param id	The Id
	 * @param name	The name
	 */
	public Library(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.creationTime = new Date();
		this.lastModificationTime = new Date();
		this.lastUpdateTime = new Date();
	}

	/**
	 * Default constructor
	 */
	public Library() {
		this.creationTime = new Date();
		this.lastModificationTime = new Date();
		this.lastUpdateTime = new Date();
	}

}

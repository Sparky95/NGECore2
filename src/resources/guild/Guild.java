/*******************************************************************************
 * Copyright (c) 2013 <Project SWG>
 * 
 * This File is part of NGECore2.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Using NGEngine to work with NGECore2 is making a combined work based on NGEngine. 
 * Therefore all terms and conditions of the GNU Lesser General Public License cover the combination.
 ******************************************************************************/
package resources.guild;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.sleepycat.persist.model.Persistent;

import resources.objects.Delta;

@Persistent(version=0)
public class Guild extends Delta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String abbreviation;
	private String name;
	private Long leader;
	private List<Long> members = new ArrayList<Long>();
	
	public Guild(int id, String abbreviation, String name, Long leader) {
		this.id = id;
		this.abbreviation = abbreviation;
		this.name = name;
		this.leader = leader;
		this.members.add(leader);
	}
	
	public Guild() {
		
	}
	
	public int getId() {
		synchronized(objectMutex) {
			return id;
		}
	}
	
	public void setId(int id) {
		synchronized(objectMutex) {
			this.id = id;
		}
	}
	
	public String getAbbreviation() {
		synchronized(objectMutex) {
			return abbreviation;
		}
	}
	
	public void setAbbreviation(String abbreviation) {
		synchronized(objectMutex) {
			this.abbreviation = abbreviation;
		}
	}
	
	public String getName() {
		synchronized(objectMutex) {
			return name;
		}
	}
	
	public void setName(String name) {
		synchronized(objectMutex) {
			this.name = name;
		}
	}
	
	public String getString() {
		return (Integer.toString(getId()) + ":" + getAbbreviation());
	}
	
	public long getLeader() {
		synchronized(objectMutex) {
			return leader;
		}
	}
	
	public void setLeader(long leader) {
		synchronized(objectMutex) {
			this.leader = leader;
		}
	}
	
	public List<Long> getMembers() {
		return members;
	}

	public byte[] getBytes() {
		synchronized(objectMutex) {
			IoBuffer buffer = createBuffer((getString().length() + 2));
			buffer.put(getAsciiString(getString()));
			return buffer.array();
		}
	}

}

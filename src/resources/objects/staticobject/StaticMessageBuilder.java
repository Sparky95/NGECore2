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
package resources.objects.staticobject;

import java.nio.ByteOrder;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;

import resources.objects.ObjectMessageBuilder;
import engine.resources.objects.Builder;

public class StaticMessageBuilder extends ObjectMessageBuilder {
	
	public StaticMessageBuilder(StaticObject object) {
		super(object);
	}
	
	public StaticMessageBuilder() {
		super();
	}
	
	public IoBuffer buildBaseline3() {

		IoBuffer buffer = bufferPool.allocate(100, false).order(ByteOrder.LITTLE_ENDIAN);
		buffer.setAutoExpand(true);
		
		buffer.putShort((short) 4);
		buffer.putInt(0);
		//buffer.putShort((short) 0);

		buffer.put(getAsciiString(object.getStfFilename()));
		buffer.putInt(0);
		//buffer.putShort((short) 0);
		buffer.put(getAsciiString(object.getStfName()));
		buffer.putInt(0);
		buffer.putInt(0xFF);
				
		int size = buffer.position();
		buffer = bufferPool.allocate(size, false).put(buffer.array(), 0, size);

		buffer.flip();
		buffer = createBaseline("STAO", (byte) 3, buffer, size);
		
		return buffer;
	}
	
	public IoBuffer buildBaseline6() {

		IoBuffer buffer = bufferPool.allocate(100, false).order(ByteOrder.LITTLE_ENDIAN);
		buffer.setAutoExpand(true);
		buffer.putShort((short) 2);
		buffer.putInt(0x43);
		//buffer.putShort((short) 0);
		buffer.put(getAsciiString(object.getDetailFilename()));
		buffer.putInt(0);
		buffer.put(getAsciiString(object.getDetailName()));
	//	buffer.putShort((short) 0);
		
		int size = buffer.position();
		buffer = bufferPool.allocate(size, false).put(buffer.array(), 0, size);

		buffer.flip();
		buffer = createBaseline("STAO", (byte) 6, buffer, size);
		
		return buffer;
	}
	
	@Override
	public void buildBaseline3(Map<Integer, Builder> baselineBuilders, Map<Integer, Builder> deltaBuilders) {
		super.buildBaseline3(baselineBuilders, deltaBuilders);
	}
	
	@Override
	public void buildBaseline6(Map<Integer, Builder> baselineBuilders, Map<Integer, Builder> deltaBuilders) {
		super.buildBaseline6(baselineBuilders, deltaBuilders);
	}
	
}
